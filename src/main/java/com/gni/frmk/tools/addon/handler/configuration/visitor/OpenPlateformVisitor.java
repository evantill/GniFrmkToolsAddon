package com.gni.frmk.tools.addon.handler.configuration.visitor;

import com.gni.frmk.tools.addon.action.wm.art.connection.DisableConnection;
import com.gni.frmk.tools.addon.action.wm.art.connection.EnableConnection;
import com.gni.frmk.tools.addon.action.wm.art.listener.DisableListener;
import com.gni.frmk.tools.addon.action.wm.art.listener.EnableListener;
import com.gni.frmk.tools.addon.action.wm.art.listener.ResumeListener;
import com.gni.frmk.tools.addon.action.wm.art.listener.SuspendListener;
import com.gni.frmk.tools.addon.action.wm.art.notifications.DisableNotification;
import com.gni.frmk.tools.addon.action.wm.art.notifications.EnableNotification;
import com.gni.frmk.tools.addon.action.wm.art.notifications.ResumeNotification;
import com.gni.frmk.tools.addon.action.wm.art.notifications.SuspendNotification;
import com.gni.frmk.tools.addon.action.wm.jms.alias.DisableJmsAlias;
import com.gni.frmk.tools.addon.action.wm.jms.alias.EnableJmsAlias;
import com.gni.frmk.tools.addon.action.wm.jms.trigger.DisableJmsTriggers;
import com.gni.frmk.tools.addon.action.wm.jms.trigger.EnableJmsTriggers;
import com.gni.frmk.tools.addon.action.wm.root.port.DisablePortListener;
import com.gni.frmk.tools.addon.action.wm.root.port.EnablePortListener;
import com.gni.frmk.tools.addon.action.wm.root.scheduler.SuspendUserTask;
import com.gni.frmk.tools.addon.action.wm.root.scheduler.WakeUpUserTask;
import com.gni.frmk.tools.addon.action.wm.root.trigger.SuspendTriggers;
import com.gni.frmk.tools.addon.api.Producer;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisited;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.state.*;
import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration;
import com.gni.frmk.tools.addon.model.configuration.component.*;
import com.gni.frmk.tools.addon.model.configuration.component.ComponentConfiguration.ComponentStateContext;
import com.google.common.collect.Lists;

import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Created by IntelliJ IDEA.
 * Date: 13/04/11
 * Time: 18:20
 *
 * @author: e03229
 */
public class OpenPlateformVisitor
        extends IgnoreComponentVisitor
        implements ConfigurationVisitor, Producer<List<Action<?>>> {

    private final List<Action<?>> openInputActions = Lists.newArrayList();
    private final List<Action<?>> openOutputActions = Lists.newArrayList();

    @Override
    public void dispatchVisit(ConfigurationVisited visitable) {
        openInputActions.clear();
        openOutputActions.clear();
        visitable.accept(this);
    }

    @Override
    public void visitConfiguration(ImmutableConfiguration configuration) {
        //TODO peut etre modifier la date de derniere modification de la configuration
    }

    @Override
    public void visitComponentConfiguration(AdapterConnectionConfiguration visited) {
        ImmutableAdapterConnection component = visited.getComponent();
        EnableState openState = visited.getStates().get(ComponentStateContext.OPEN);
        switch (openState.getEnabled()) {
            case ENABLED:
                openOutput(new EnableConnection(component.getAlias()));
                break;
            case DISABLED:
                openOutput(new DisableConnection(component.getAlias()));
                break;
        }
    }

    @Override
    public void visitComponentConfiguration(AdapterListenerConfiguration visited) {
        ImmutableAdapterListener component = visited.getComponent();
        ActivableState openState = visited.getStates().get(ComponentStateContext.OPEN);
        switch (openState.getEnabled()) {
            case ENABLED:
                openInput(new EnableListener(component.getName()));
                break;
            case DISABLED:
                openInput(new DisableListener(component.getName()));
                break;
        }
        switch (openState.getActivable()) {
            case ACTIVE:
                openInput(new ResumeListener(component.getName()));
                break;
            case INACTIVE:
                openInput(new SuspendListener(component.getName()));
                break;
        }
    }

    @Override
    public void visitComponentConfiguration(AdapterNotificationConfiguration visited) {
        ImmutableAdapterNotification component = visited.getComponent();
        ActivableState openState = visited.getStates().get(ComponentStateContext.OPEN);
        switch (openState.getEnabled()) {
            case ENABLED:
                openInput(new EnableNotification(component.getName()));
                break;
            case DISABLED:
                openInput(new DisableNotification(component.getName()));
                break;
        }
        switch (openState.getActivable()) {
            case ACTIVE:
                openInput(new ResumeNotification(component.getName()));
                break;
            case INACTIVE:
                openInput(new SuspendNotification(component.getName()));
                break;
        }
    }

    @Override
    public void visitComponentConfiguration(PortConfiguration visited) {
        ImmutablePort component = visited.getComponent();
        ActivableState openState = visited.getStates().get(ComponentStateContext.OPEN);
        switch (openState.getEnabled()) {
            case ENABLED:
                openInput(new EnablePortListener(component.getPackageName(), component.getKey()));
                break;
            case DISABLED:
                openInput(new DisablePortListener(component.getPackageName(), component.getKey()));
                break;
        }
    }

    @Override
    public void visitComponentConfiguration(SchedulerConfiguration visited) {
        ImmutableScheduler component = visited.getComponent();
        SchedulerState openState = visited.getStates().get(ComponentStateContext.OPEN);
        switch (openState.getEnabled()) {
            case ENABLED:
                openInput(new WakeUpUserTask(component.getOid()));
                break;
            case DISABLED:
                openInput(new SuspendUserTask(component.getOid()));
                break;
        }
    }

    /**
     * Le changement de statut des triggers natifs est le suivant :
     * <code>
     * <ul>
     * <li>Enable/Permanent -> Enable/Permanent</li>
     * <li>Disable/Permanent -> Disable/Permanent</li>
     * <li>Enable/Temporary -> Enable/Temporary</li>
     * <li>Disable/Temporary -> Disable/Permanent</li>
     * </ul>
     * </code>
     *
     * @param visited the trigger configuration to apply
     */
    @Override
    public void visitComponentConfiguration(NativeTriggerConfiguration visited) {
        ImmutableNativeTrigger component = visited.getComponent();
        String triggerName = component.getName();
        NativeTriggerState openState = visited.getStates().get(ComponentStateContext.OPEN);
        //Normally, disabling/enabling a trigger is only done in DEV so we will ignore disabled triggers
        switch (openState.getEnabled().getEnabled()) {
            case DISABLED:
                //ignore it
                return;
        }
        //change ENABLED Trigger state
        //change processing state
        TemporaryActivableState processingState = openState.getProcessingState();
        SuspendTriggers openProcessingAction = SuspendTriggers.builder()
                                                              .addTriggerName(triggerName)
                                                              .applyChangeAcrossCluster(false)
                                                              .persistChange(processingState.getTemporary()
                                                                                            .isPermanent())
                                                              .suspendProcessing(processingState.getActivable()
                                                                                                .isNotActive())
                                                              .suspendRetrieval(true)
                                                              .build();
        openInput(openProcessingAction);
        //change retreivalstate
        TemporaryActivableState retrievalState = openState.getRetrievalState();
        SuspendTriggers openRetrievalAction = SuspendTriggers.builder()
                                                             .addTriggerName(triggerName)
                                                             .applyChangeAcrossCluster(false)
                                                             .persistChange(retrievalState.getTemporary().isPermanent())
                                                             .suspendProcessing(processingState.getActivable()
                                                                                               .isNotActive())
                                                             .suspendRetrieval(retrievalState.getActivable()
                                                                                             .isNotActive())
                                                             .build();
        openInput(openRetrievalAction);
    }

    /**
     * At the end, Jms trigger is a tri state component : ENABLED,DISABLED,SUSPENDED.
     * Opening Rule:
     * <ul>
     * <li>ENABLED-->ENABLED</li>
     * <li>DISABLED-->DISABLED</li>
     * <li>SUSPENDED--><b>DISABLED</b>&nbsp;<i>(changing from DISABLED to SUSPENDED is not possible)</i>.</li>
     * <ul>
     *
     * @param visited
     */
    //TODO change status to a tri state one
    @Override
    public void visitComponentConfiguration(JmsTriggerConfiguration visited) {
        ImmutableJmsTrigger component = visited.getComponent();
        ActivableState openState = visited.getStates().get(ComponentStateContext.OPEN);
        switch (openState.getActivable()) {
            case INACTIVE:
                openInput(new DisableJmsTriggers(component.getName()));
                break;
            case ACTIVE:
                switch (openState.getEnabled()) {
                    case ENABLED:
                        openInput(new EnableJmsTriggers(component.getName()));
                        break;
                    case DISABLED:
                        openInput(new DisableJmsTriggers(component.getName()));
                        break;
                }
                break;
        }
    }

    @Override
    public void visitComponentConfiguration(JmsAliasConfiguration visited) {
        ImmutableJmsAlias component = visited.getComponent();
        ConnectableState openState = visited.getStates().get(ComponentStateContext.OPEN);
        switch (openState.getEnabled()) {
            case ENABLED:
                openOutput(new EnableJmsAlias(component.getName()));
                break;
            case DISABLED:
                openOutput(new DisableJmsAlias(component.getName()));
                break;
        }
    }

    @Override
    public void visitComponentConfiguration(IntegrationServerPackageConfiguration visited) {
        //TODO a implementer dans un deuxieme temps
    }

    @Override
    public List<Action<?>> produce() {
        List<Action<?>> produced = Lists.newArrayList();
        produced.addAll(openOutputActions);
        produced.addAll(openInputActions);
        return unmodifiableList(produced);
    }

    private void openInput(Action<?> action) {
        openInputActions.add(action);
    }

    private void openOutput(Action<?> action) {
        openOutputActions.add(action);
    }

}
