package com.gni.frmk.tools.addon.operation.handler.component.bytype;

import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.api.Result;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 08/06/11
 * Time: 17:11
 *
 * @author: e03229
 */
public abstract class ActionOnComponentTypeContext
        <
                T extends ComponentType<?, ?, ?, ?, ?>,
                S extends ActionOnComponentTypeStrategy<R, A, E>,
                R extends Result,
                A extends ActionOnComponentType<T, R>,
                E extends ExecutionContext
                >
        implements ActionOnComponentTypeStrategy<R, A, E> {

    private final Map<T, S> strategies = Maps.newHashMap();

    public void register(T componentType, S strategy) {
        strategies.put(componentType, strategy);
    }

    @Override
    public R execute(A action, E context) throws ActionException {
        return strategies.get(action.getType()).execute(action, context);
    }
}
