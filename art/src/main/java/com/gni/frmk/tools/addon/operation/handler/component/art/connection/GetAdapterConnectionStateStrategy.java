package com.gni.frmk.tools.addon.operation.handler.component.art.connection;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterConnectionInfo;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterTypeInput;
import com.gni.frmk.tools.addon.invoker.service.art.ListAdapterConnections;
import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnectionType;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
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
public class GetAdapterConnectionStateStrategy
        implements GetComponentStateStrategy<AdapterConnectionType, AdapterId, EnableState> {

    private final ListAdapterConnections connections = new ListAdapterConnections();

    @Override
    public AdapterConnectionType getComponentType() {
        return AdapterConnectionType.TYPE;
    }

    @Override
    public EnableState getState(final AdapterId componentId, InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        AdapterTypeInput input = AdapterTypeInput.newInstance(componentId.getAdapterType());
        ListValueOutput<AdapterConnectionInfo> infos = connections.invoke(input, serviceContext);
        AdapterConnectionInfo info = infos.getSingleValue(new Predicate<AdapterConnectionInfo>() {
            @Override
            public boolean apply(AdapterConnectionInfo input) {
                return input != null &&
                       componentId.getName().equals(input.getConnectionAlias());
            }
        });
        EnableStatus enabled = EnableStatus.UNKNOWN;
        switch (info.getConnectionState()) {
            case ENABLED:
                enabled = EnableStatus.ENABLED;
                break;
            case DISABLED:
                enabled = EnableStatus.DISABLED;
                break;
        }
        return EnableState.build(enabled);
    }

}
