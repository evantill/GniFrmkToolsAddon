package com.gni.frmk.tools.addon.operation.handler.component.jms.alias;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.SingleValueInput;
import com.gni.frmk.tools.addon.invoker.service.jms.DisableConnectionAlias;
import com.gni.frmk.tools.addon.invoker.service.jms.EnableConnectionAlias;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.ConnectableState;
import com.gni.frmk.tools.addon.model.component.jms.JmsAliasType;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ChangeComponentStateHandler.ChangeComponentStateStrategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 15/06/11
 * Time: 14:19
 *
 * @author: e03229
 */
public class ChangeJmsAliasStateStrategy
        implements ChangeComponentStateStrategy<JmsAliasType, StringId, ConnectableState> {

    private final DisableConnectionAlias disableService = new DisableConnectionAlias();
    private final EnableConnectionAlias enableService = new EnableConnectionAlias();

    @Override
    public ConnectableState changeState(StringId componentId, ConnectableState oldState, ConnectableState newState, InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        SingleValueInput<String> serviceInput = SingleValueInput.newInstance(componentId.getValue());
        switch (newState.getEnabled()) {
            default:
            case UNKNOWN:
                break;
            case ENABLED:
                enableService.invoke(serviceInput, serviceContext);
                break;
            case DISABLED:
                disableService.invoke(serviceInput, serviceContext);
                break;
        }
        return newState;
    }

    @Override
    public JmsAliasType getComponentType() {
        return JmsAliasType.TYPE;
    }
}
