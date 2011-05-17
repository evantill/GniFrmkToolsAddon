package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.Component.Detail;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.model.component.Component.State;
import com.gni.frmk.tools.addon.operation.action.component.GetAllComponents;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;
import com.gni.frmk.tools.addon.operation.api.*;
import com.gni.frmk.tools.addon.operation.result.ListResult;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 14:20
 *
 * @author: e03229
 */
public abstract class GetAllComponentsHandler
        <I extends Id,
                D extends Detail,
                S extends State,
                C extends Component<I, S, D>,
                A extends GetAllComponents<C>,
                CTX extends ExecutionContext>
        implements ActionHandler<A, ListResult<C>, CTX> {

    @Override
    public final ListResult<C> execute(A action, CTX context) throws ActionException {
        try {
            List<C> components = Lists.newArrayList();
            List<I> idList = this.<ListResult<I>>execute(context, newListIdAction()).getCollection();
            for (I id : idList) {
                D detail = this.execute(context, newGetComponentDetailAction(id)).getValue();
                S state = this.execute(context, newGetComponentStateAction(id)).getValue();
                C component = newComponent(id, detail, state);
                components.add(component);
            }
            return new ListResult<C>(components);
        } catch (DispatchException e) {
            throw new ActionException(action, e);
        }
    }

    protected <R extends Result> R execute(ExecutionContext context, Action<R> action) throws DispatchException {
        return context.getDispatcher().execute(action);
    }

    protected abstract Action<ListResult<I>> newListIdAction();

    protected abstract GetComponentDetail<D, I> newGetComponentDetailAction(I id);

    protected abstract GetComponentState<S, I> newGetComponentStateAction(I id);

    protected abstract C newComponent(I id, D detail, S state) throws DispatchException;


}
