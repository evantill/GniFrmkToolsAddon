package com.gni.frmk.tools.addon.operation.handler.component;


import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.util.TypeLiteral;
import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 13:53
 *
 * @author: e03229
 */
public class GetComponentStateHandler
        implements ActionHandler<GetComponentState<?, ?>, SingleResult<? extends ComponentState<?>>, InvokeContext> {

    private static final TypeLiteral<GetComponentState<?, ?>> TYPE_LITERAL = new TypeLiteral<GetComponentState<?, ?>>() {
    };

    public static interface GetComponentStateStrategy
            <T extends ComponentType<T, ?, I, S, ?>, I extends ComponentId<I>, S extends ComponentState<S>>
            extends ActionStrategy<T> {

        S getState(I componentId, InvokeContext context) throws ServiceException;
    }

    private final ActionContext<GetComponentStateStrategy<?, ?, ?>> strategyContext;

    @Produces
    public static ActionContext<GetComponentStateStrategy<?, ?, ?>> newActionContext(Instance<GetComponentStateStrategy<?, ?, ?>> strategies) {
        return ActionContext.newContext(strategies);
    }

    @Inject
    public GetComponentStateHandler(ActionContext<GetComponentStateStrategy<?, ?, ?>> strategyContext) {
        this.strategyContext = strategyContext;
    }

    @Override
    public SingleResult<? extends ComponentState<?>> execute(GetComponentState<?, ?> action, InvokeContext context) throws ActionException {
        return executeTypeSafe(action, context);
    }

    public <I extends ComponentId<I>, S extends ComponentState<S>>
    SingleResult<S> executeTypeSafe(GetComponentState<I, S> action, InvokeContext context) throws ActionException {
        try {
            GetComponentStateStrategy<?, I, S> strategy = strategyContext.selectStrategy(action.getComponentType());
            if (strategy == null) {
                throw new ActionException(action, String.format("strategy not found for %s", action.getComponentType()));
            }
            return SingleResult.newInstance(strategy.getState(action.getComponentId(), context));
        } catch (ServiceException e) {
            throw new ActionException(action, e);
        }
    }

    @Override
    public TypeLiteral<GetComponentState<?, ?>> getActionType() {
        return TYPE_LITERAL;
    }
}
