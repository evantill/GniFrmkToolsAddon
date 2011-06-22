package com.gni.frmk.tools.addon.operation.handler.component.root.scheduler;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.root.SchedulerInfo;
import com.gni.frmk.tools.addon.invoker.service.root.GetUserTaskList;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.SchedulerState;
import com.gni.frmk.tools.addon.model.component.root.SchedulerStatus;
import com.gni.frmk.tools.addon.model.component.root.SchedulerType;
import com.gni.frmk.tools.addon.model.component.root.SuspendedStatus;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentStateHandler.GetComponentStateStrategy;
import com.google.common.base.Predicate;
import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class GetSchedulerStateStrategy
        implements GetComponentStateStrategy<SchedulerType, StringId, SchedulerState> {

    private final GetUserTaskList schedulers;

    @Inject
    public GetSchedulerStateStrategy(GetUserTaskList schedulers) {
        this.schedulers = schedulers;
    }

    @Override
    public SchedulerType getComponentType() {
        return SchedulerType.TYPE;
    }

    @Override
    public SchedulerState getState(final StringId componentId, InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        ListValueOutput<SchedulerInfo> infos = schedulers.invoke(NoInput.singleton, serviceContext);
        SchedulerInfo info = infos.getSingleValue(new Predicate<SchedulerInfo>() {
            @Override
            public boolean apply(SchedulerInfo input) {
                return input != null && componentId.getValue().equals(input.getOid());
            }
        });
        SuspendedStatus suspended = SuspendedStatus.fromBoolean(info.isSuspended());
        SchedulerStatus scheduler = SchedulerStatus.fromBoolean(info.isExpired());
        return SchedulerState.build(suspended, scheduler);
    }

}
