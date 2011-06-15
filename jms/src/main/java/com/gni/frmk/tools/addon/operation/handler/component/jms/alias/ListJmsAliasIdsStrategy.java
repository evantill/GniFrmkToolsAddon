package com.gni.frmk.tools.addon.operation.handler.component.jms.alias;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.jms.JmsAliasInfo;
import com.gni.frmk.tools.addon.invoker.service.jms.GetConnectionAliasReport;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsAliasType;
import com.gni.frmk.tools.addon.operation.handler.InvokeContext;
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
public class ListJmsAliasIdsStrategy
        implements ListComponentIdsStrategy<JmsAliasType, StringId> {

    private final GetConnectionAliasReport aliases = new GetConnectionAliasReport();

    @Override
    public Set<StringId> listIds(InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        Set<StringId> ids = Sets.newHashSet();
        for (JmsAliasInfo info : aliases.invoke(NoInput.singleton, serviceContext).getValues()) {
            StringId id = JmsAliasType.TYPE.idBuilder()
                                           .value(info.getAliasName())
                                           .validate().build();
            ids.add(id);
        }
        return ids;
    }

    @Override
    public JmsAliasType getComponentType() {
        return JmsAliasType.TYPE;
    }
}
