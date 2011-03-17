package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.invoke.exceptions.DispatchException;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractInvokeHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 14:31
 *
 * @author: e03229
 */
public abstract class InvokeDispatcher implements Dispatch {

    protected abstract SimpleServiceRegistry getHandlerRegistry();

    protected abstract ExecutionContext getExecutionContext();

    @Override
    public <A extends Action<R>, R extends Result> R execute(A action) throws DispatchException {
        ActionHandler<A, R, ExecutionContext> handler = getHandlerRegistry().findHandler(action);
        return handler.execute(action, getExecutionContext());
    }

    public void registerHandler(AbstractInvokeHandler<?, ?> handler) {
        getHandlerRegistry().addHandler(handler);
    }
}
