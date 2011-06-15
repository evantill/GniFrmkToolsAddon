package com.gni.frmk.tools.addon.operation.handler.component.root.trigger;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.root.NativeTriggerInfo;
import com.gni.frmk.tools.addon.invoker.service.root.GetTriggerReport;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.NativeTriggerType;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsHandler.ListComponentIdsStrategy;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class ListNativeTriggerIdsStrategy
        implements ListComponentIdsStrategy<NativeTriggerType, StringId> {

    private final GetTriggerReport triggers = new GetTriggerReport();

    @Override
    public Set<StringId> listIds(InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        Set<StringId> ids = Sets.newHashSet();
        for (NativeTriggerInfo info : triggers.invoke(NoInput.singleton, serviceContext).getValues()) {
            StringId id = NativeTriggerType.TYPE.idBuilder()
                                                .value(info.getName())
                                                .validate().build();
            ids.add(id);
        }
        return ids;
    }

    @Override
    public NativeTriggerType getComponentType() {
        return NativeTriggerType.TYPE;
    }
}
