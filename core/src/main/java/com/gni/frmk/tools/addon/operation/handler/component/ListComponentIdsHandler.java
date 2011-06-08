package com.gni.frmk.tools.addon.operation.handler.component;


import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentIds;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.handler.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.SetResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 13:53
 *
 * @author: e03229
 */
public class ListComponentIdsHandler
        implements ActionHandler<
        ListComponentIds<? extends ComponentId<?>>,
        SetResult<? extends ComponentId<?>>,
        InvokeContext> {

    private final ListComponentIdsContext strategyContext;

    public ListComponentIdsHandler(ListComponentIdsContext strategyContext) {
        this.strategyContext = strategyContext;
    }

    @Override
    public SetResult<? extends ComponentId<?>> execute(ListComponentIds<? extends ComponentId<?>> action, InvokeContext context) throws ActionException {
        return executeTypeSafe(action, context);
    }

    public <I extends ComponentId<?>> SetResult<I> executeTypeSafe(ListComponentIds<I> action, InvokeContext context) throws ActionException {
        return strategyContext.<I>execute(action, context);
    }

    @Override
    public Class<?> getActionType() {
        return ListComponentIds.class;
    }
}
