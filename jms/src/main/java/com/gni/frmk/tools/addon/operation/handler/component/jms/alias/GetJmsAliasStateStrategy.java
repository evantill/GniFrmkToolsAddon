package com.gni.frmk.tools.addon.operation.handler.component.jms.alias;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.jms.JmsAliasInfo;
import com.gni.frmk.tools.addon.invoker.service.jms.GetConnectionAliasReport;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.ConnectableState;
import com.gni.frmk.tools.addon.model.component.jms.ConnectableStatus;
import com.gni.frmk.tools.addon.model.component.jms.JmsAliasType;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentStateHandler.GetComponentStateStrategy;
import com.google.common.base.Predicate;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class GetJmsAliasStateStrategy
        implements GetComponentStateStrategy<JmsAliasType, StringId, ConnectableState> {

    private final GetConnectionAliasReport aliases = new GetConnectionAliasReport();

    @Override
    public JmsAliasType getComponentType() {
        return JmsAliasType.TYPE;
    }

    @Override
    public ConnectableState getState(final StringId componentId, InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();

        ListValueOutput<JmsAliasInfo> infos = aliases.invoke(NoInput.singleton, serviceContext);
        JmsAliasInfo info = infos.getSingleValue(new Predicate<JmsAliasInfo>() {
            @Override
            public boolean apply(JmsAliasInfo input) {
                return input != null &&
                       componentId.getValue().equals(input.getAliasName());
            }
        });
        EnableStatus enabled = EnableStatus.fromBoolean(info.isEnabled());
        ConnectableStatus connected = ConnectableStatus.fromBoolean(info.isConnected());
        return ConnectableState.build(enabled, connected);

    }
}
