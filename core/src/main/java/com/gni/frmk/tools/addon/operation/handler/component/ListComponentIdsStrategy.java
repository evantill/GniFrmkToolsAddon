package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentIds;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.handler.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.SetResult;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:36
 *
 * @author: e03229
 */
public abstract class ListComponentIdsStrategy<T extends ComponentType<?, ?, I, ?, ?>, I extends ComponentId<?>> {

    public SetResult<I> execute(ListComponentIds<I> action, InvokeContext executionContext) throws ActionException {
        try {
            return SetResult.newInstance(listIds(executionContext));
        } catch (ServiceException cause) {
            throw new ActionException(action, cause);
        }
    }

    protected abstract Set<I> listIds(InvokeContext context) throws ServiceException;

    public abstract T getType();
}
