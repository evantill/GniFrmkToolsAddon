package com.gni.frmk.tools.addon.operation.handler.component.root.scheduler;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.root.SchedulerInfo;
import com.gni.frmk.tools.addon.invoker.service.root.GetUserTaskList;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.SchedulerDetail;
import com.gni.frmk.tools.addon.model.component.root.SchedulerType;
import com.gni.frmk.tools.addon.operation.handler.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentDetailHandler.GetComponentDetailStrategy;
import com.google.common.base.Predicate;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class GetSchedulerDetailStrategy
        implements GetComponentDetailStrategy<SchedulerType, StringId, SchedulerDetail> {

    private final GetUserTaskList schedulers = new GetUserTaskList();

    @Override
    public SchedulerType getComponentType() {
        return SchedulerType.TYPE;
    }

    @Override
    public SchedulerDetail getDetail(final StringId componentId, InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        ListValueOutput<SchedulerInfo> infos = schedulers.invoke(NoInput.singleton, serviceContext);
        SchedulerInfo info = infos.getSingleValue(new Predicate<SchedulerInfo>() {
            @Override
            public boolean apply(SchedulerInfo input) {
                return input != null && componentId.getValue().equals(input.getOid());
            }
        });
        return SchedulerDetail.builder()
                              .name(info.getName())
                              .description(info.getDescription())
                              .schedulerType(info.getType())
                              .service(info.getService())
                              .validate().build();
    }

}
