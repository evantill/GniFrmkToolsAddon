package com.gni.frmk.tools.addon.operation.handler.component.bytype;

import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.api.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 08/06/11
 * Time: 17:09
 *
 * @author: e03229
 */
public abstract class ActionOnComponentTypeHandler
        <
                S extends ActionOnComponentTypeStrategy<R, A, E>,
                T extends ComponentType<?, ?, ?, ?, ?>,
                A extends ActionOnComponentType<T, R>,
                R extends Result,
                E extends ExecutionContext
                >
        implements ActionHandler<A, R, E> {

    private final ActionOnComponentTypeContext<T, S, R, A, E> strategyContext;

    protected ActionOnComponentTypeHandler(ActionOnComponentTypeContext<T, S, R, A, E> strategyContext) {
        this.strategyContext = strategyContext;
    }

    @Override
    public R execute(A action, E context) throws ActionException {
        return strategyContext.execute(action, context);
    }
}
