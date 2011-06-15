package com.gni.frmk.tools.addon.operation.handler.component.art.connection;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterComponentId;
import com.gni.frmk.tools.addon.invoker.service.art.DisableConnection;
import com.gni.frmk.tools.addon.invoker.service.art.EnableConnection;
import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnectionType;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.handler.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ChangeComponentStateHandler.ChangeComponentStateStrategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 15/06/11
 * Time: 14:19
 *
 * @author: e03229
 */
public class ChangeAdapterConnectionStateStrategy
        implements ChangeComponentStateStrategy<AdapterConnectionType, AdapterId, EnableState> {

    private final EnableConnection enableService = new EnableConnection();
    private final DisableConnection disableService = new DisableConnection();

    @Override
    public EnableState changeState(AdapterId componentId, EnableState oldState, EnableState newState, InvokeContext context) throws ServiceException {
        String requestType = componentId.getAdapterType();
        String requestName = componentId.getName();
        ServiceContext serviceContext = context.getServiceContext();
        switch (newState.getEnabled()) {
            case UNKNOWN:
                break;
            case ENABLED:
                enableService.invoke(AdapterComponentId.newInstance(requestType, requestName), serviceContext);
                break;
            case DISABLED:
                disableService.invoke(AdapterComponentId.newInstance(requestType, requestName), serviceContext);
                break;
        }
        return newState;
    }

    @Override
    public AdapterConnectionType getComponentType() {
        return AdapterConnectionType.TYPE;
    }
}
