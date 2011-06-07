package com.gni.frmk.tools.addon.visitor;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.visitor.api.VisitorContext;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 17:52
 *
 * @author: e03229
 */
public class InvokeVisitorContext implements VisitorContext {
    private final ServiceContext serviceContext;
    private final ExecutionContext executionContext;

    public InvokeVisitorContext(ServiceContext serviceContext, ExecutionContext executionContext) {
        this.serviceContext = serviceContext;
        this.executionContext = executionContext;
    }

    public ServiceContext getServiceContext() {
        return serviceContext;
    }

    public ExecutionContext getExecutionContext() {
        return executionContext;
    }
}
