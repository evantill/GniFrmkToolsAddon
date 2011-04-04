package com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api;

import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.api.ActionException;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.api.Dispatch;
import com.gni.frmk.tools.addon.command.api.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 14:31
 *
 * @author: e03229
 */
public class InvokeDispatcher implements Dispatch {

    private final InvokeServiceRegistry handlerRegistry;
    private final InvokeContext invokeContext;

    public InvokeDispatcher(InvokeServiceRegistry handlerRegistry, InvokeContext invokeContext) {
        this.handlerRegistry = handlerRegistry;
        this.invokeContext = invokeContext;
    }

    protected InvokeServiceRegistry getHandlerRegistry() {
        return handlerRegistry;
    }

    protected InvokeContext getInvokeContext() {
        return invokeContext;
    }

    @Override
    public <A extends Action<R>, R extends Result> R execute(A action) throws ActionException {
        ActionHandler<A, R, InvokeContext> handler = getHandlerRegistry().findInvoker(action);
        return handler.execute(action, getInvokeContext());
    }

    public <H extends ActionHandler<?,?,? extends InvokeContext>> void registerHandler(H handler) {
        getHandlerRegistry().addHandler(handler);
    }
}
