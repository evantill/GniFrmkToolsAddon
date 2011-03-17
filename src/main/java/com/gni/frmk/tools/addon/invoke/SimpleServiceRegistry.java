package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.invoke.exceptions.ActionNotFoundException;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 19:12
 *
 * @author: e03229
 */
public class SimpleServiceRegistry implements ActionHandlerRegistry {

    private final Map<Class<? extends Action<?>>, ActionHandler<?, ?, ?>> handlers = Maps.newHashMap();

    public void addHandler(ActionHandler<?, ?, ?> handler) {
        handlers.put(handler.getActionType(), handler);
    }

    @SuppressWarnings({"unchecked",
                       "SuspiciousMethodCalls"})
    @Override
    public <A extends Action<R>, R extends Result, E extends ExecutionContext> ActionHandler<A, R, E> findHandler(A action)
            throws ActionNotFoundException {
        ActionHandler<A, R, E> handler = (ActionHandler<A, R, E>) handlers.get(action.getClass());
        if (handler == null) {
            throw new ActionNotFoundException(action);
        }
        return handler;
    }

    @Override
    public void clearHandlers() {
        handlers.clear();
    }
}