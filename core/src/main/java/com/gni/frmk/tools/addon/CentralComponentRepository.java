package com.gni.frmk.tools.addon;

import com.gni.frmk.tools.addon.ComponentRepository.ListId;
import com.gni.frmk.tools.addon.ComponentRepository.StateAction;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.operation.api.Dispatcher;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;
import com.gni.frmk.tools.addon.model.component.Component.Detail;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.model.component.Component.State;
import com.gni.frmk.tools.addon.model.component.Component.Type;
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

    private final Map<Type, ComponentRepository<?, ?, ?, ?>> repositories= Maps.newHashMap();

    public void x(Dispatcher d){

    }
    @SuppressWarnings("unchecked")
    <I extends Id> ListResult<I> listIds(Type type, ExecutionContext context) throws DispatchException {
        ListId<I> action = ((ComponentRepository<?, I, ?, ?>) repositories.get(type)).getListIdAction();
        return context.getDispatcher().execute(action);
    }

    @SuppressWarnings("unchecked")
    <I extends Id, D extends Detail> SingleResult<D> detailAction(Type type, I id, ExecutionContext context) throws DispatchException {
        GetComponentDetail<D,I> action = ((ComponentRepository<?, I, ?, D>) repositories.get(type)).getDetailAction(id);
        return context.getDispatcher().execute(action);
    }

    @SuppressWarnings("unchecked")
    <I extends Id, S extends State> SingleResult<S> stateAction(Type type, I id, ExecutionContext context) throws DispatchException {
        StateAction<I, S> action = ((ComponentRepository<?, I, S, ?>) repositories.get(type)).getStateAction(id);
        return context.getDispatcher().execute(action);
    }
}
