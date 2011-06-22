package com.gni.frmk.tools.addon.operation.handler.component.jms.trigger;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.jms.JmsTriggerInfo;
import com.gni.frmk.tools.addon.invoker.service.jms.GetJmsTriggerReport;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsTriggerType;
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
public class ListJmsTriggerIdsStrategy
        implements ListComponentIdsStrategy<JmsTriggerType, StringId> {

    private final GetJmsTriggerReport triggers;

    @Inject
    public ListJmsTriggerIdsStrategy(GetJmsTriggerReport triggers) {
        this.triggers = triggers;
    }

    @Override
    public Set<StringId> listIds(InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        Set<StringId> ids = Sets.newHashSet();
        for (JmsTriggerInfo info : triggers.invoke(NoInput.singleton, serviceContext).getValues()) {
            StringId id = JmsTriggerType.TYPE.idBuilder()
                                             .value(info.getNodeName())
                                             .validate().build();
            ids.add(id);
        }
        return ids;
    }

    @Override
    public JmsTriggerType getComponentType() {
        return JmsTriggerType.TYPE;
    }
}
