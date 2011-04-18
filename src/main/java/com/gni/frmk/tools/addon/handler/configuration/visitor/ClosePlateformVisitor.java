package com.gni.frmk.tools.addon.handler.configuration.visitor;

import com.gni.frmk.tools.addon.action.wm.art.connection.DisableConnection;
import com.gni.frmk.tools.addon.action.wm.art.listener.DisableListener;
import com.gni.frmk.tools.addon.action.wm.art.notifications.DisableNotification;
import com.gni.frmk.tools.addon.action.wm.jms.alias.DisableJmsAlias;
import com.gni.frmk.tools.addon.action.wm.jms.trigger.DisableJmsTriggers;
import com.gni.frmk.tools.addon.action.wm.root.port.DisablePortListener;
import com.gni.frmk.tools.addon.action.wm.root.scheduler.SuspendUserTask;
import com.gni.frmk.tools.addon.action.wm.root.service.WaitServicesEnd;
import com.gni.frmk.tools.addon.action.wm.root.trigger.SuspendTriggers;
import com.gni.frmk.tools.addon.api.Producer;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisited;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.state.TemporaryActivableState;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.component.*;
import com.google.common.collect.Lists;

import java.util.List;

import static com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus.ENABLED;
import static com.gni.frmk.tools.addon.model.component.state.TemporaryActivableState.TemporaryStatus.PERMANENT;
import static com.gni.frmk.tools.addon.model.configuration.component.ComponentConfiguration.ComponentStateContext.CURRENT;
import static com.gni.frmk.tools.addon.model.configuration.component.ComponentConfiguration.ComponentStateContext.OPEN;
import static java.util.Collections.unmodifiableList;

/**
 * Created by IntelliJ IDEA.
 * Date: 13/04/11
 * Time: 17:38
 *
 * @author: e03229
 */
public class ClosePlateformVisitor
        implements
        ConfigurationVisitor,
        Producer<List<Action<?>>> {

    private final List<Action<?>> closeInputActions = Lists.newArrayList();
    private final List<Action<?>> closeOutputActions = Lists.newArrayList();
    private final WaitServicesEnd waitServiceEndAction;

    public ClosePlateformVisitor(WaitServicesEnd waitServiceEndAction) {
        this.waitServiceEndAction = waitServiceEndAction;
    }

    @Override
    public void dispatchVisit(ConfigurationVisited visitable) {
        closeInputActions.clear();
        closeOutputActions.clear();
        visitable.accept(this);
    }

    @Override
    public void visitComponent(AdapterConnection visited) {
        closeOutput(new DisableConnection(visited.getAlias()));
    }

    @Override
    public void visitComponent(AdapterListener visited) {
        closeInput(new DisableListener(visited.getName()));
    }

    @Override
    public void visitComponent(AdapterNotification visited) {
        closeInput(new DisableNotification(visited.getName()));
    }

    @Override
    public void visitComponent(IntegrationServerPackage visited) {
        //TODO a implementer dans un deuxieme temps
    }

    @Override
    public void visitComponent(Port visited) {
        closeInput(new DisablePortListener(visited.getPackageName(), visited.getKey()));
    }

    @Override
    public void visitComponent(Scheduler visited) {
        closeInput(new SuspendUserTask(visited.getOid()));
    }

    @Override
    public void visitComponent(NativeTrigger visited) {
        SuspendTriggers action = SuspendTriggers.builder()
                                                .addTriggerName(visited.getName())
                                                .applyChangeAcrossCluster(false)
                                                .persistChange(true)
                                                .suspendProcessing(true)
                                                .suspendRetrieval(true)
                                                .build();
        closeInput(action);
    }

    @Override
    public void visitComponent(JmsTrigger visited) {
        closeInput(new DisableJmsTriggers(visited.getName()));
    }

    @Override
    public void visitComponent(JmsAlias visited) {
        closeOutput(new DisableJmsAlias(visited.getName()));
    }

    @Override
    public void visitConfiguration(Configuration configuration) {
        //TODO peut etre modifier la date de derniere modification de la configuration
    }

    @Override
    public void visitComponentConfiguration(AdapterConnectionConfiguration visited) {
        prepareStatusForNextOpen(visited);
    }

    @Override
    public void visitComponentConfiguration(AdapterListenerConfiguration visited) {
        prepareStatusForNextOpen(visited);
    }

    @Override
    public void visitComponentConfiguration(AdapterNotificationConfiguration visited) {
        prepareStatusForNextOpen(visited);
    }

    @Override
    public void visitComponentConfiguration(PortConfiguration visited) {
        prepareStatusForNextOpen(visited);
    }

    @Override
    public void visitComponentConfiguration(SchedulerConfiguration visited) {
        prepareStatusForNextOpen(visited);
    }

    @Override
    public void visitComponentConfiguration(NativeTriggerConfiguration visited) {
        //handle special case of disabled temporary
        NativeTriggerState currentState = visited.getComponent().getState();
        //if enabled and has temporary suspended then change to permanent suspended
        boolean enabled = currentState.getEnabled().getEnabled().isEnabled();
        TemporaryActivableState processingState = currentState.getProcessingState();
        boolean processingTemporarySuspended =
                processingState.getTemporary().isTemporary() && processingState.getActivable().isNotActive();
        TemporaryActivableState retrievalState = currentState.getRetrievalState();
        boolean retreivalTemporarySuspended =
                retrievalState.getTemporary().isTemporary() && retrievalState.getActivable().isNotActive();
        if (enabled && (processingTemporarySuspended || retreivalTemporarySuspended)) {
            //prepare for reopen : OPEN will permanent suspended
            TemporaryActivableState updateProcessingState = new TemporaryActivableState(PERMANENT, processingState.getActivable());
            TemporaryActivableState updateRetrievalState = new TemporaryActivableState(PERMANENT, retrievalState.getActivable());
            NativeTriggerState updatesCurrentState = NativeTriggerState.builder().defineEnable(ENABLED)
                                                                       .defineProcessing(updateProcessingState)
                                                                       .defineRetrieval(updateRetrievalState)
                                                                       .build();
            visited.getStates().put(OPEN, updatesCurrentState);
            //define current state as requested state for close operation
            visited.getStates().put(CURRENT, updatesCurrentState);
        } else {
            prepareStatusForNextOpen(visited);
        }
    }

    @Override
    public void visitComponentConfiguration(JmsTriggerConfiguration visited) {
        //handle special case of suspended triggers
        ActivableState current = visited.getComponent().getState();
        boolean enabled = current.getEnabled().isEnabled();
        boolean suspended = current.getActivable().isNotActive();
        if (enabled && suspended) {
            //prepare for reopen : OPEN will disabled
            ActivableState updatesCurrentState = new ActivableState(EnableStatus.DISABLED, ActivableStatus.INACTIVE);
            visited.getStates().put(OPEN, updatesCurrentState);
            //define current state as requested state for close operation
            visited.getStates().put(CURRENT, updatesCurrentState);
        } else {
            prepareStatusForNextOpen(visited);
        }
    }

    @Override
    public void visitComponentConfiguration(JmsAliasConfiguration visited) {
        prepareStatusForNextOpen(visited);
    }

    @Override
    public void visitComponentConfiguration(IntegrationServerPackageConfiguration visited) {
        prepareStatusForNextOpen(visited);
    }

    @Override
    public List<Action<?>> produce() {
        List<Action<?>> produced = Lists.newArrayList();
        produced.addAll(closeInputActions);
        produced.add(waitServiceEndAction);
        produced.addAll(closeOutputActions);
        return unmodifiableList(produced);
    }

    private <C extends AbstractComponent<?, S>, S extends AbstractComponentState>
    void prepareStatusForNextOpen(ComponentConfiguration<C, S> visited) {
        C component = visited.getComponent();
        S currentState = component.getState();
        //prepare for reopen : OPEN will be the current state
        visited.getStates().put(OPEN, currentState);
        //define current state as requested state for close operation
        visited.getStates().put(CURRENT, currentState);
    }

    private void closeInput(Action<?> action) {
        closeInputActions.add(action);
    }

    private void closeOutput(Action<?> action) {
        closeOutputActions.add(action);
    }

}
