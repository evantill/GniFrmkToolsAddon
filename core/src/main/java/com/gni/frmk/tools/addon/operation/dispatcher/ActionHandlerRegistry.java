package com.gni.frmk.tools.addon.operation.dispatcher;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.ActionNotFoundException;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.api.Result;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 15:30
 *
 * @author: e03229
 */
public class ActionHandlerRegistry<C extends ExecutionContext> {

    public static final class HandlerRegistration<E extends ExecutionContext> {
        private final ActionHandlerRegistry<E> registry;
        private final ActionHandler<?, ?, ? extends E> handler;

        public HandlerRegistration(ActionHandlerRegistry<E> registry, ActionHandler<?, ?, ? extends E> handler) {
            this.registry = registry;
            this.handler = handler;
        }

        public void unregister() {
            registry.unregister(handler);
        }
    }

    private final Map<Class, ActionHandler<?, ?, ? extends C>> handlers = Maps.newHashMap();

    public HandlerRegistration register(ActionHandler<?, ?, ? extends C> handler) {
        handlers.put(handler.getActionType(), handler);
        return new HandlerRegistration<C>(this, handler);
    }

    public void unregister(ActionHandler<?, ?, ? extends C> handler) {
        handlers.remove(handler.getActionType());
    }

    public void unregisterAll() {
        handlers.clear();
    }

    @SuppressWarnings("unchecked")
    public <A extends Action<R>, R extends Result, CC extends C> ActionHandler<A, R, CC> getHandler(A action)
            throws ActionNotFoundException {
        ActionHandler<A, R, CC> handler = (ActionHandler<A, R, CC>) handlers.get(action.getClass());
        if (handler == null) {
            throw new ActionNotFoundException(action);
        }
        return handler;
    }


}
