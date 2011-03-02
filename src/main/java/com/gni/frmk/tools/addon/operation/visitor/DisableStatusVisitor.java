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
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
public class DisableStatusVisitor implements ConfigurationVisitor {

    public void visit(AdapterConnection visited) {
        visited.getState().setEnableStatus(ComponentState.EnableStatus.DISABLED);
    }

    public void visit(AdapterListener visited) {
        visited.getState().setEnableStatus(ComponentState.EnableStatus.DISABLED);
        visited.getState().setActiveStatus(ActivableComponentState.ActiveStatus.SUSPENDED);
    }

    public void visit(AdapterNotification visited) {
        visited.getState().setEnableStatus(ComponentState.EnableStatus.DISABLED);
        visited.getState().setActiveStatus(ActivableComponentState.ActiveStatus.SUSPENDED);
    }

    public void visit(Port visited) {
        visited.getState().setEnableStatus(ComponentState.EnableStatus.DISABLED);
        visited.getState().setActiveStatus(ActivableComponentState.ActiveStatus.SUSPENDED);
    }

    public void visit(Scheduler visited) {
        //TODO  a corriger
        // visited.setExecutionState(Scheduler.ExecutionState.SUSPENDED);
//        visited.setSchedulerState(Scheduler.SchedulerState.UNEXPIRED);
    }

    public void visit(JmsAlias visited) {
        visited.getState().setEnableStatus(ComponentState.EnableStatus.DISABLED);
        visited.getState().setActiveStatus(ActivableComponentState.ActiveStatus.SUSPENDED);
    }

    public void visit(JmsTrigger visited) {
        visited.setStatus(Trigger.Status.DISABLED);
        visited.setExecutionState(Trigger.State.SUSPENDED);
    }

    public void visit(NativeTrigger visited) {
        visited.setStatus(Trigger.Status.DISABLED);
        visited.setProcessingState(new NativeTrigger.NativeState(Trigger.State.SUSPENDED,
                Trigger.TemporalStatus.PERMANENT));
        visited.setRetrievalState(new NativeTrigger.NativeState(Trigger.State.SUSPENDED,
                Trigger.TemporalStatus.PERMANENT));
    }

    public void clear() {
    }
}
