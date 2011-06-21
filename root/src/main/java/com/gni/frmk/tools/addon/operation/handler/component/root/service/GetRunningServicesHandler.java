package com.gni.frmk.tools.addon.operation.handler.component.root.service;

import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.service.root.GetAllServiceStats;
import com.gni.frmk.tools.addon.operation.action.component.root.service.GetRunningServices;
import com.gni.frmk.tools.addon.operation.action.component.root.service.GetRunningServices.Result;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.google.inject.Inject;

import java.util.Set;

import static com.google.common.collect.Sets.filter;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 20:22
 *
 * @author: e03229
 */
public class GetRunningServicesHandler
        implements ActionHandler<GetRunningServices, Result, InvokeContext> {

    private final GetAllServiceStats service;

    @Inject
    public GetRunningServicesHandler(GetAllServiceStats service) {
        this.service = service;
    }

    @Override
    public Class<GetRunningServices> getActionType() {
        return GetRunningServices.class;
    }

    @Override
    public Result execute(GetRunningServices action, InvokeContext context) throws ActionException {
        try {
            Set<String> services = service.invoke(NoInput.singleton, context.getServiceContext()).getValues();
            return new Result(filter(services, action.getFilter()));
        } catch (ServiceException cause) {
            throw new ActionException(action, cause);
        }
    }
}
