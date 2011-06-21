package com.gni.frmk.tools.addon.operation.handler.component.art;

import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.service.art.RetrieveAdapterTypes;
import com.gni.frmk.tools.addon.operation.action.component.art.ListAdapterTypes;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.SetResult;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:24
 *
 * @author: e03229
 */
public class ListAdapterTypesHandler
        implements ActionHandler<ListAdapterTypes, SetResult<String>, InvokeContext> {

    private final RetrieveAdapterTypes service;

    @Inject
    public ListAdapterTypesHandler(RetrieveAdapterTypes service) {
        this.service = service;
    }

    @Override
    public SetResult<String> execute(ListAdapterTypes action, InvokeContext context) throws ActionException {
        try {
            return SetResult.newInstance(service.invoke(NoInput.singleton, context.getServiceContext()).getValues());
        } catch (ServiceException cause) {
            throw new ActionException(action, cause);
        }
    }

    @Override
    public Class<ListAdapterTypes> getActionType() {
        return ListAdapterTypes.class;
    }
}
