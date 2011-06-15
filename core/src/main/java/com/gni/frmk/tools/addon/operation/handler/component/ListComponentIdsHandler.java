package com.gni.frmk.tools.addon.operation.handler.component;


import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentIds;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.handler.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.SetResult;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 13:53
 *
 * @author: e03229
 */
public class ListComponentIdsHandler
        implements ActionHandler<ListComponentIds<?>, SetResult<? extends ComponentId<?>>, InvokeContext> {

    public static interface ListComponentIdsStrategy
            <T extends ComponentType<T, ?, I, ?, ?>, I extends ComponentId<I>>
            extends ActionStrategy<T> {

        Set<I> listIds(InvokeContext context) throws ServiceException;
    }

    private final ActionContext<ListComponentIdsStrategy<?, ?>> strategyContext;

    public ListComponentIdsHandler(ActionContext<ListComponentIdsStrategy<?, ?>> strategyContext) {
        this.strategyContext = strategyContext;
    }

    @Override
    public SetResult<? extends ComponentId<?>> execute(ListComponentIds<?> action, InvokeContext context) throws ActionException {
        return executeTypeSafe(action, context);
    }

    public <I extends ComponentId<I>> SetResult<I> executeTypeSafe(ListComponentIds<I> action, InvokeContext context) throws ActionException {
        try {
            ListComponentIdsStrategy<?, I> strategy = strategyContext.selectStrategy(action.getComponentType());
            if (strategy == null) {
                throw new ActionException(action, String.format("strategy not found for %s", action.getComponentType()));
            }
            return SetResult.newInstance(strategy.listIds(context));
        } catch (ServiceException e) {
            throw new ActionException(action, e);
        }
    }

    @Override
    public Class<?> getActionType() {
        return ListComponentIds.class;
    }

}
