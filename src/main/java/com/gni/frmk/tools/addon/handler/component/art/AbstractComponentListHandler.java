package com.gni.frmk.tools.addon.handler.component.art;

import com.gni.frmk.tools.addon.api.action.*;
import com.gni.frmk.tools.addon.model.Component;
import com.gni.frmk.tools.addon.model.Component.Detail;
import com.gni.frmk.tools.addon.model.Component.Id;
import com.gni.frmk.tools.addon.model.Component.State;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.gni.frmk.tools.addon.result.ComponentStateResult;
import com.gni.frmk.tools.addon.result.ListResult;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 14:20
 *
 * @author: e03229
 */
public abstract class AbstractComponentListHandler<I extends Id, D extends Detail, S extends State, C extends Component<I, S, D>, A extends Action<ListResult<C>>, CTX extends ExecutionContext>
        implements ActionHandler<A, ListResult<C>, CTX> {

    @Override
    public final ListResult<C> execute(A action, CTX context) throws ActionException {
        try {
            List<C> components = Lists.newArrayList();
            List<I> idList = this.<ListResult<I>>execute(context, newListIdAction()).getCollection();
            for (I id : idList) {
                D detail = this.<ComponentDetailResult<D>>execute(context, newGetComponentDetailAction(id)).getValue();
                S state = this.<ComponentStateResult<S>>execute(context, newGetComponentStateAction(id)).getValue();
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

    protected abstract Action<ComponentDetailResult<D>> newGetComponentDetailAction(I id);

    protected abstract Action<ComponentStateResult<S>> newGetComponentStateAction(I id);

    protected abstract C newComponent(I id, D detail, S state) throws DispatchException;


}
