package com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api;

import com.gni.frmk.tools.addon.command.api.*;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.handler.wm.InvokeHandler;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 19:12
 *
 * @author: e03229
 */
public class InvokeServiceRegistry implements ActionHandlerRegistry {

    private final Map<Class<? extends Action<?>>, ActionHandler<?, ?, ? extends InvokeContext>> handlers = Maps.newHashMap();

    public InvokeServiceRegistry() {
    }

    public InvokeServiceRegistry(Collection<InvokeHandler<?, ?>> registry) {
        for (InvokeHandler<?, ?> h : registry) {
            handlers.put(h.getActionType(), h);
        }
    }

    public <H extends ActionHandler<?, ?, ? extends InvokeContext>> void addHandler(H handler) {
        handlers.put(handler.getActionType(), handler);
    }

    @SuppressWarnings({"unchecked"})
    public <A extends Action<R>, R extends Result, E extends ExecutionContext> ActionHandler<A, R, E> findHandler(A action) throws ActionNotFoundException {
        return (ActionHandler<A, R, E>) findInvoker(action);
    }

    @SuppressWarnings({"unchecked"})
    public <A extends Action<R>, R extends Result, I extends InvokeContext> ActionHandler<A, R, I> findInvoker(A action) throws ActionNotFoundException {
        ActionHandler<?, ?, ? extends InvokeContext> handler = handlers.get(action.getClass());
        if (handler == null) {
            throw new ActionNotFoundException(action);
        }
        return (ActionHandler<A, R, I>) handler;
    }

    @Override
    public void clearHandlers() {
        handlers.clear();
    }

}
