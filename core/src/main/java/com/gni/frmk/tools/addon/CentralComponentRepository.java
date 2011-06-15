package com.gni.frmk.tools.addon;

import com.gni.frmk.tools.addon.ComponentRepository.ListId;
import com.gni.frmk.tools.addon.ComponentRepository.StateAction;
import com.gni.frmk.tools.addon.model.component.ComponentDetail;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.operation.api.Dispatcher;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.result.ListResult;
import com.gni.frmk.tools.addon.operation.result.SingleResult;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 14:07
 *
 * @author: e03229
 */
public class CentralComponentRepository {

    private final Map<ComponentType, ComponentRepository<?, ?, ?, ?,?>> repositories = Maps.newHashMap();

    public void x(Dispatcher d) {

    }

    @SuppressWarnings("unchecked")
    <I extends ComponentId> ListResult<I> listIds(ComponentType type, ExecutionContext context) throws DispatchException {
        ListId<I> action = ((ComponentRepository<?,?, I, ?, ?>) repositories.get(type)).getListIdAction();
        return context.getDispatcher().execute(action);
    }

    @SuppressWarnings("unchecked")
    <I extends ComponentId<I>, D extends ComponentDetail<D>> SingleResult<D> detailAction(ComponentType type, I id, ExecutionContext context) throws DispatchException {
        GetComponentDetail<I,D> action = ((ComponentRepository<?,?, I, ?, D>) repositories.get(type)).getDetailAction(id);
        return context.getDispatcher().execute(action);
    }

    @SuppressWarnings("unchecked")
    <I extends ComponentId<I>, S extends ComponentState<S>> SingleResult<S> stateAction(ComponentType type, I id, ExecutionContext context) throws DispatchException {
        StateAction<I, S> action = ((ComponentRepository<?, ?,I, S, ?>) repositories.get(type)).getStateAction(id);
        return context.getDispatcher().execute(action);
    }
}
