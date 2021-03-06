package com.gni.frmk.tools.addon.operation.handler.component.root.scheduler;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.root.SchedulerInfo;
import com.gni.frmk.tools.addon.invoker.service.root.GetUserTaskList;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.SchedulerType;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsHandler.ListComponentIdsStrategy;
import com.google.common.collect.Sets;
import javax.inject.Inject;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class ListSchedulerIdsStrategy
        implements ListComponentIdsStrategy<SchedulerType, StringId> {

    private final GetUserTaskList schedulers;

    @Inject
    public ListSchedulerIdsStrategy(GetUserTaskList schedulers) {
        this.schedulers = schedulers;
    }

    @Override
    public Set<StringId> listIds(InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        Set<StringId> ids = Sets.newHashSet();
        for (SchedulerInfo info : schedulers.invoke(NoInput.singleton, serviceContext).getValues()) {
            StringId id = SchedulerType.TYPE.idBuilder()
                                            .value(info.getOid())
                                            .validate().build();
            ids.add(id);
        }
        return ids;
    }

    @Override
    public SchedulerType getComponentType() {
        return SchedulerType.TYPE;
    }
}
