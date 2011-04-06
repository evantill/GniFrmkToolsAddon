package com.gni.frmk.tools.addon.service.configuration;

import com.gni.frmk.tools.addon.command.action.wm.art.DisableConnection;
import com.gni.frmk.tools.addon.command.action.wm.art.DisableListener;
import com.gni.frmk.tools.addon.command.action.wm.art.DisableNotification;
import com.gni.frmk.tools.addon.command.action.wm.jms.DisableJmsAlias;
import com.gni.frmk.tools.addon.command.action.wm.jms.SuspendJmsTriggers;
import com.gni.frmk.tools.addon.command.action.wm.root.DisablePackage;
import com.gni.frmk.tools.addon.command.action.wm.root.DisablePortListener;
import com.gni.frmk.tools.addon.command.action.wm.root.SuspendTriggers;
import com.gni.frmk.tools.addon.command.action.wm.root.SuspendUserTask;
import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.api.Result;
import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.service.configuration.strategy.CloseStrategy;

import static com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration.ComponentStateContext.CLOSE;
import static com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration.ComponentStateContext.OPEN;

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

    @Override
    protected <C extends AbstractComponent<?, S>, S extends AbstractComponentState>
    void visitComponentConfiguration(ComponentConfiguration<C, S> visited) {
        S currentState = visited.getComponent().getState();
        //prepare for reopen : OPEN will be the current state
        visited.getStates().put(OPEN, currentState);
        //define current state as requested state for close operation
        visited.getComponent().setState(visited.getStates().get(CLOSE));
    }

    @Override
    public void visit(AdapterConnection visited) {
        DisableConnection command = new DisableConnection(visited.getAlias());
        dispatch(command);
    }

    @Override
    public void visit(AdapterListener visited) {
        DisableListener command = new DisableListener(visited.getName());
        dispatch(command);
    }

    @Override
    public void visit(AdapterNotification visited) {
        DisableNotification command = new DisableNotification(visited.getName());
        dispatch(command);
    }

    @Override
    public void visit(Port visited) {
        DisablePortListener command = new DisablePortListener(visited.getPackageName(), visited.getKey());
        dispatch(command);
    }

    @Override
    public void visit(Scheduler visited) {
        SuspendUserTask command = new SuspendUserTask(visited.getOid());
        dispatch(command);
    }

    @Override
    public void visit(NativeTrigger visited) {
        SuspendTriggers command = SuspendTriggers.builder()
                                                 .addTriggerName(visited.getName())
                                                 .applyChangeAcrossCluster(false)
                                                 .persistChange(true)
                                                 .suspendProcessing(true)
                                                 .suspendRetrieval(true)
                                                 .build();
        dispatch(command);
    }

    @Override
    public void visit(JmsTrigger visited) {
        SuspendJmsTriggers command = new SuspendJmsTriggers(visited.getName());
        dispatch(command);
    }

    @Override
    public void visit(JmsAlias visited) {
        DisableJmsAlias command = new DisableJmsAlias(visited.getName());
        dispatch(command);
    }

    @Override
    public void visit(IntegrationServerPackage visited) {
        DisablePackage command = new DisablePackage(visited.getPackageName());
        dispatch(command);
    }

    public abstract <A extends Action<R>, R extends Result> R dispatch(A command);
}
