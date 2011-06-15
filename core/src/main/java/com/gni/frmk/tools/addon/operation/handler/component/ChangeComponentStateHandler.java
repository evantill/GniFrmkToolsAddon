package com.gni.frmk.tools.addon.operation.handler.component;


import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.action.component.ChangeComponentState;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.operation.handler.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 13:53
 *
 * @author: e03229
 */
public class ChangeComponentStateHandler
        implements ActionHandler<ChangeComponentState<?, ?>, SingleResult<? extends ComponentState<?>>, InvokeContext> {

    public static interface ChangeComponentStateStrategy
            <T extends ComponentType<T, ?, I, S, ?>, I extends ComponentId<I>, S extends ComponentState<S>>
            extends ActionStrategy<T> {

        S changeState(I componentId, S oldState, S newState, InvokeContext context) throws ServiceException;
    }

    private final ActionContext<ChangeComponentStateStrategy<?, ?, ?>> strategyContext;

    public ChangeComponentStateHandler(ActionContext<ChangeComponentStateStrategy<?, ?, ?>> strategyContext) {
        this.strategyContext = strategyContext;
    }

    @Override
    public SingleResult<? extends ComponentState<?>> execute(ChangeComponentState<?, ?> action, InvokeContext context) throws ActionException {
        return executeTypeSafe(action, context);
    }

    public <I extends ComponentId<I>, S extends ComponentState<S>>
    SingleResult<S> executeTypeSafe(ChangeComponentState<I, S> action, InvokeContext context) throws ActionException {
        try {
            ComponentType<?, ?, I, S, ?> componentType = action.getComponentType();
            S newState = action.getNewState();
            S oldState = action.getOldState();
            I componentId = action.getComponentId();

            ChangeComponentStateStrategy<?, I, S> strategy = strategyContext.selectStrategy(componentType);
            if (strategy == null) {
                throw new ActionException(action, String.format("strategy not found for %s", componentType));
            }
            try {
                S changedState = strategy.changeState(componentId, oldState, newState, context);
                S refreshedState = refreshState(componentType, componentId, context);
                if (changedState.equals(refreshedState)) {
                    String message = String.format("component %s state is %s but should be %s", componentId, refreshedState, changedState);
                    throw new ActionException(action, message);
                }
                return SingleResult.newInstance(refreshedState);
            } catch (DispatchException cause) {
                throw new ActionException(action, String.format("can not refresh component %s state", componentId), cause);
            }
        } catch (ServiceException e) {
            throw new ActionException(action, e);
        }
    }

    private <T extends ComponentType<?, ?, I, S, ?>, I extends ComponentId<I>, S extends ComponentState<S>>
    S refreshState(T componentType, I componentId, InvokeContext context) throws DispatchException {
        GetComponentState<I, S> action = GetComponentState.newInstance(componentType, componentId);
        return context.getDispatcher().execute(action).getValue();
    }

    @Override
    public Class<?> getActionType() {
        return ChangeComponentState.class;
    }

}
