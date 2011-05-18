package com.gni.frmk.tools.addon.operation.handler.component.root.scheduler;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.SchedulerState;
import com.gni.frmk.tools.addon.model.component.root.SuspendedStatus;
import com.gni.frmk.tools.addon.operation.action.component.root.scheduler.ChangeSchedulerState;
import com.gni.frmk.tools.addon.operation.action.component.root.scheduler.SuspendUserTask;
import com.gni.frmk.tools.addon.operation.action.component.root.scheduler.WakeUpUserTask;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.handler.component.ChangeComponentStateHandler;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 10:36
 *
 * @author: e03229
 */
public class ChangeSchedulerStateHandler extends ChangeComponentStateHandler<ChangeSchedulerState, StringId, SchedulerState, InvokeContext> {

    @Override
    protected List<Action<?>> defineActions(SchedulerState oldState, SchedulerState newState, StringId id) {
        SuspendedStatus oldSuspended = oldState.getSuspended();
        SuspendedStatus newSuspended = newState.getSuspended();
        if (SuspendedStatus.isActivation(oldSuspended, newSuspended)) {
            return singleAction(new WakeUpUserTask(id));
        }
        if (SuspendedStatus.isDesactivation(oldSuspended, newSuspended)) {
            return singleAction(new SuspendUserTask(id));
        }
        return NO_ACTION;
    }

    @Override
    public Class<? extends ChangeSchedulerState> getActionType() {
        return ChangeSchedulerState.class;
    }
}
