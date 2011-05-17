package com.gni.frmk.tools.addon.operation.dispatcher;

import com.gni.frmk.tools.addon.operation.api.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 15:30
 *
 * @author: e03229
 */
public class SimpleDispatcher<C extends ExecutionContext> implements Dispatcher {

    private final ActionHandlerRegistry<C> registry;
    private final C context;

    public SimpleDispatcher(ActionHandlerRegistry<C> registry, C context) {
        this.registry = registry;
        this.context = context;
    }

    @Override
    public <A extends Action<R>, R extends Result> R execute(A action) throws DispatchException {
        ActionHandler<A, R, C> handler = registry.<A, R, C>getHandler(action);
        return handler.execute(action, context);
    }
}
