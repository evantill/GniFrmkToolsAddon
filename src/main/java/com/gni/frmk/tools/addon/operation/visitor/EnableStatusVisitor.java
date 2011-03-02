package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.data.adapter.AdapterConnection;
import com.gni.frmk.tools.addon.data.adapter.AdapterListener;
import com.gni.frmk.tools.addon.data.adapter.AdapterNotification;
import com.gni.frmk.tools.addon.data.component.ActivableComponentState;
import com.gni.frmk.tools.addon.data.component.ComponentState;
import com.gni.frmk.tools.addon.data.port.Port;
import com.gni.frmk.tools.addon.data.scheduler.Scheduler;
import com.gni.frmk.tools.addon.data.trigger.JmsAlias;
import com.gni.frmk.tools.addon.data.trigger.JmsTrigger;
import com.gni.frmk.tools.addon.data.trigger.NativeTrigger;
import com.gni.frmk.tools.addon.data.trigger.Trigger;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:42
 * To change this template use File | Settings | File Templates.
 */
public class EnableStatusVisitor implements ConfigurationVisitor {

    public void visit(AdapterConnection visited) {
        visited.getState().setEnableStatus(ComponentState.EnableStatus.ENABLED);
    }

    public void visit(AdapterListener visited) {
        visited.getState().setEnableStatus(ComponentState.EnableStatus.ENABLED);
        visited.getState().setActiveStatus(ActivableComponentState.ActiveStatus.ACTIVE);
    }

    public void visit(AdapterNotification visited) {
        visited.getState().setEnableStatus(ComponentState.EnableStatus.ENABLED);
        visited.getState().setActiveStatus(ActivableComponentState.ActiveStatus.ACTIVE);
    }

    public void visit(Port visited) {
        visited.getState().setEnableStatus(ComponentState.EnableStatus.ENABLED);
        visited.getState().setActiveStatus(ActivableComponentState.ActiveStatus.ACTIVE);
    }

    public void visit(Scheduler visited) {
        //TODO  a corriger
        //visited.setExecutionState(Scheduler.ExecutionState.READY);
        //visited.setSchedulerState(Scheduler.SchedulerState.UNEXPIRED);
    }

    public void visit(JmsAlias visited) {
        visited.getState().setEnableStatus(ComponentState.EnableStatus.ENABLED);
        visited.getState().setActiveStatus(ActivableComponentState.ActiveStatus.ACTIVE);
    }

    public void visit(JmsTrigger visited) {
        visited.setStatus(Trigger.Status.ENABLED);
        visited.setExecutionState(Trigger.State.ACTIVE);
    }

    public void visit(NativeTrigger visited) {
        visited.setStatus(Trigger.Status.ENABLED);
        visited.setProcessingState(new NativeTrigger.NativeState(Trigger.State.ACTIVE,
                Trigger.TemporalStatus.PERMANENT));
        visited.setRetrievalState(new NativeTrigger.NativeState(Trigger.State.ACTIVE,
                Trigger.TemporalStatus.PERMANENT));
    }

    public void clear() {
    }
}
