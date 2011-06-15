package com.gni.frmk.tools.addon.operation.handler.component.root.scheduler;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.SingleValueInput;
import com.gni.frmk.tools.addon.invoker.service.root.SuspendUserTask;
import com.gni.frmk.tools.addon.invoker.service.root.WakeupUserTask;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.SchedulerState;
import com.gni.frmk.tools.addon.model.component.root.SchedulerType;
import com.gni.frmk.tools.addon.model.component.root.SuspendedStatus;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ChangeComponentStateHandler.ChangeComponentStateStrategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 15/06/11
 * Time: 14:19
 *
 * @author: e03229
 */
public class ChangeSchedulerStateStrategy
        implements ChangeComponentStateStrategy<SchedulerType, StringId, SchedulerState> {

    private final WakeupUserTask wakeupService = new WakeupUserTask();
    private final SuspendUserTask suspendService = new SuspendUserTask();

    @Override
    public SchedulerState changeState(StringId componentId, SchedulerState oldState, SchedulerState newState, InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        SingleValueInput<String> serviceInput = SingleValueInput.newInstance(componentId.getValue());
        SuspendedStatus oldSuspended = oldState.getSuspended();
        SuspendedStatus newSuspended = newState.getSuspended();
        if (SuspendedStatus.isActivation(oldSuspended, newSuspended)) {
            wakeupService.invoke(serviceInput, serviceContext);
        } else if (SuspendedStatus.isDesactivation(oldSuspended, newSuspended)) {
            suspendService.invoke(serviceInput, serviceContext);
        }
        return newState;
    }

    @Override
    public SchedulerType getComponentType() {
        return SchedulerType.TYPE;
    }
}
