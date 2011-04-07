package com.gni.frmk.tools.addon.service.configuration;

import com.gni.frmk.tools.addon.command.action.wm.art.connection.DisableConnection;
import com.gni.frmk.tools.addon.command.action.wm.art.listener.DisableListener;
import com.gni.frmk.tools.addon.command.action.wm.art.notifications.DisableNotification;
import com.gni.frmk.tools.addon.command.action.wm.jms.alias.DisableJmsAlias;
import com.gni.frmk.tools.addon.command.action.wm.jms.trigger.SuspendJmsTriggers;
import com.gni.frmk.tools.addon.command.action.wm.root.ispackage.DisablePackage;
import com.gni.frmk.tools.addon.command.action.wm.root.port.DisablePortListener;
import com.gni.frmk.tools.addon.command.action.wm.root.trigger.SuspendTriggers;
import com.gni.frmk.tools.addon.command.action.wm.root.scheduler.SuspendUserTask;
import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.model.configuration.component.*;
import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationProcessingStrategy;
import com.gni.frmk.tools.addon.service.configuration.strategy.CloseStrategy;

import static com.gni.frmk.tools.addon.model.configuration.component.ComponentConfiguration.ComponentStateContext.CURRENT;
import static com.gni.frmk.tools.addon.model.configuration.component.ComponentConfiguration.ComponentStateContext.OPEN;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 20:38
 *
 * @author: e03229
 */
public abstract class ClosePlateform extends AbstractService {

    public ClosePlateform() {
        super(new CloseStrategy());
    }

    protected ClosePlateform(ConfigurationProcessingStrategy strategy) {
        super(strategy);
    }

    private <C extends AbstractComponent<?, S>, S extends AbstractComponentState>
        void prepareStatusForNextOpen(ComponentConfiguration<C, S> visited){
            C component = visited.getComponent();
        S currentState = component.getState();
        //prepare for reopen : OPEN will be the current state
        visited.getStates().put(OPEN, currentState);
        //define current state as requested state for close operation
        visited.getStates().put(CURRENT, currentState);
    }

    @Override
    public void visit(AdapterConnectionConfiguration visited) {
        prepareStatusForNextOpen(visited);
        AdapterConnection component = visited.getComponent();
        DisableConnection command = new DisableConnection(component.getAlias());
        dispatch(command);
    }

    @Override
    public void visit(AdapterListenerConfiguration visited) {
        prepareStatusForNextOpen(visited);
        AdapterListener component = visited.getComponent();
        DisableListener command = new DisableListener(component.getName());
        dispatch(command);
    }

    @Override
    public void visit(AdapterNotificationConfiguration visited) {
        prepareStatusForNextOpen(visited);
        AdapterNotification component = visited.getComponent();
        DisableNotification command = new DisableNotification(component.getName());
        dispatch(command);
    }

    @Override
    public void visit(PortConfiguration visited) {
        prepareStatusForNextOpen(visited);
        Port component = visited.getComponent();
        DisablePortListener command = new DisablePortListener(component.getPackageName(), component.getKey());
        dispatch(command);
    }

    @Override
    public void visit(SchedulerConfiguration visited) {
        prepareStatusForNextOpen(visited);
        Scheduler component = visited.getComponent();
        SuspendUserTask command = new SuspendUserTask(component.getOid());
        dispatch(command);
    }

    @Override
    public void visit(NativeTriggerConfiguration visited) {
        prepareStatusForNextOpen(visited);
        NativeTrigger component = visited.getComponent();
        SuspendTriggers command = SuspendTriggers.builder()
                                                 .addTriggerName(component.getName())
                                                 .applyChangeAcrossCluster(false)
                                                 .persistChange(true)
                                                 .suspendProcessing(true)
                                                 .suspendRetrieval(true)
                                                 .build();
        dispatch(command);
    }

    @Override
    public void visit(JmsTriggerConfiguration visited) {
        prepareStatusForNextOpen(visited);
        JmsTrigger component = visited.getComponent();
        SuspendJmsTriggers command = new SuspendJmsTriggers(component.getName());
        dispatch(command);
    }

    @Override
    public void visit(JmsAliasConfiguration visited) {
        prepareStatusForNextOpen(visited);
        JmsAlias component = visited.getComponent();
        DisableJmsAlias command = new DisableJmsAlias(component.getName());
        dispatch(command);
    }

    @Override
    public void visit(IntegrationServerPackageConfiguration visited) {
        prepareStatusForNextOpen(visited);
        IntegrationServerPackage component = visited.getComponent();
        DisablePackage command = new DisablePackage(component.getPackageName());
        dispatch(command);
    }
}
