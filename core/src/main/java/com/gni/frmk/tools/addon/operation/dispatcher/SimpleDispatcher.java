package com.gni.frmk.tools.addon.operation.dispatcher;

import com.gni.frmk.tools.addon.operation.api.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 15:30
 *
 * @author: e03229
 */
public abstract class SimpleDispatcher<C extends ExecutionContext> implements Dispatcher {

    private final ActionHandlerRegistry<C> registry;

    protected SimpleDispatcher(ActionHandlerRegistry<C> registry) {
        this.registry = registry;
    }

    @Override
    public <A extends Action<R>, R extends Result> R execute(A action) throws DispatchException {
        ActionHandler<A, R, C> handler = registry.getHandler(action);
        return handler.execute(action, createContext());
    }

    @Override
    public <A extends Action<R>, R extends Result, F extends Action<?>> R executeFromAction(F from, A action) throws ActionException {
        try {
            return execute(action);
        } catch (DispatchException cause) {
            throw new ActionException(from, cause);
        }
    }

    public abstract C createContext();

}
