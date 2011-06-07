package com.gni.frmk.tools.addon.operation.handler;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.operation.api.Dispatcher;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 17:47
 *
 * @author: e03229
 */
public class InvokeContext implements ExecutionContext {
    private final Dispatcher dispatcher;
    private final ServiceContext serviceContext;

    public InvokeContext(Dispatcher dispatcher, ServiceContext serviceContext) {
        this.dispatcher = dispatcher;
        this.serviceContext = serviceContext;
    }

    @Override
    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public ServiceContext getServiceContext() {
        return serviceContext;
    }
}
