package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentIds;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.handler.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.SetResult;
import com.gni.frmk.tools.addon.visitor.api.VisitorContext;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 13:53
 *
 * @author: e03229
 */
public class ListComponentIdsHandler
        implements ActionHandler<ListComponentIds<?, ?>, SetResult<?>, InvokeContext> {

    private final ListComponentIdsContext strategyContext;

    public ListComponentIdsHandler(ListComponentIdsContext strategyContext) {
        this.strategyContext = strategyContext;
    }

    @Override
    public Class<?> getActionType() {
        return ListComponentIds.class;
    }

    public <T extends ComponentId<T>> SetResult<T> executeTypeSafe(ListComponentIds<T, ?> action, InvokeContext context) throws ActionException {
        try {
            return SetResult.newInstance(strategyContext.listIds(action.getType(), context));
        } catch (ServiceException e) {
            throw new ActionException(action, e);
        }
    }

    @Override
    public SetResult<?> execute(ListComponentIds<?, ?> action, InvokeContext context) throws ActionException {
        return executeTypeSafe(action, context);
    }

    public static final class Context implements VisitorContext {
        private ExecutionContext context;
        private final List<ComponentId> ids = Lists.newArrayList();

        public Context(ExecutionContext context) {
            this.context = context;
        }

        public ExecutionContext getContext() {
            return context;
        }

        public void addId(ComponentId id) {
            ids.add(id);
        }

        public <I> List<I> getIds(Class<I> idType) {
            List<I> checkedValues = Lists.newArrayList();
            for (ComponentId id : ids) {
                checkedValues.add(idType.cast(id));
            }
            return checkedValues;
        }
    }
}
