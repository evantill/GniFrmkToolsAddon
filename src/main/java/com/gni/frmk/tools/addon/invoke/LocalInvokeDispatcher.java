package com.gni.frmk.tools.addon.invoke;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/03/11
 * Time: 17:25
 *
 * @author: e03229
 */
public class LocalInvokeDispatcher extends InvokeDispatcher {
    private final InvokeServiceRegistry handlerRegistry;
    private final InvokeContext invokeContext;

    public LocalInvokeDispatcher(InvokeServiceRegistry handlerRegistry, InvokeContext invokeContext) {
        this.handlerRegistry = handlerRegistry;
        this.invokeContext = invokeContext;
    }

    @Override
    protected InvokeServiceRegistry getHandlerRegistry() {
        return handlerRegistry;
    }

    @Override
    protected InvokeContext getInvokeContext() {
        return invokeContext;
    }
}
