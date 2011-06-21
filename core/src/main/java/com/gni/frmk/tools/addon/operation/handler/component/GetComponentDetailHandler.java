package com.gni.frmk.tools.addon.operation.handler.component;


import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.model.component.ComponentDetail;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentIds;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.SingleResult;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 13:53
 *
 * @author: e03229
 */
public class GetComponentDetailHandler
        implements ActionHandler<GetComponentDetail<?, ?>, SingleResult<? extends ComponentDetail<?>>, InvokeContext> {

    public static interface GetComponentDetailStrategy
            <T extends ComponentType<T, ?, I, ?, D>, I extends ComponentId<I>, D extends ComponentDetail<D>>
            extends ActionStrategy<T> {

        D getDetail(I componentId, InvokeContext context) throws ServiceException;
    }

    private final ActionContext<GetComponentDetailStrategy<?, ?, ?>> strategyContext;

    @Inject
    public GetComponentDetailHandler(ActionContext<GetComponentDetailStrategy<?, ?, ?>> strategyContext) {
        this.strategyContext = strategyContext;
    }

    @Override
    public SingleResult<? extends ComponentDetail<?>> execute(GetComponentDetail<?, ?> action, InvokeContext context) throws ActionException {
        return executeTypeSafe(action, context);
    }

    public <I extends ComponentId<I>, D extends ComponentDetail<D>>
    SingleResult<D> executeTypeSafe(GetComponentDetail<I, D> action, InvokeContext context) throws ActionException {
        try {
            GetComponentDetailStrategy<?, I, D> strategy = strategyContext.selectStrategy(action.getComponentType());
            if (strategy == null) {
                throw new ActionException(action, String.format("strategy not found for %s", action.getComponentType()));
            }
            return SingleResult.newInstance(strategy.getDetail(action.getComponentId(), context));
        } catch (ServiceException e) {
            throw new ActionException(action, e);
        }
    }

    @Override
    public Class<?> getActionType() {
        return ListComponentIds.class;
    }

}
