package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.operation.action.component.ComponentAction;
import com.gni.frmk.tools.addon.model.component.Component.Detail;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.model.component.Component.State;
import com.gni.frmk.tools.addon.operation.api.*;
import com.gni.frmk.tools.addon.operation.result.ComponentDetailResult;
import com.gni.frmk.tools.addon.operation.result.ComponentStateResult;
import com.gni.frmk.tools.addon.operation.result.ListResult;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 11:44
 *
 * @author: e03229
 */
public abstract class AbstractComponentHandler<I extends Id, D extends Detail, S extends State, C extends Component<I, S, D>, A extends ComponentAction<I, ListResult<C>>, CTX extends ExecutionContext>
        implements ActionHandler<A, SingleResult<C>, CTX> {

    @Override
    public final SingleResult<C> execute(A action, CTX context) throws ActionException {
        try {
            C component = defineComponent(action.getId(), context);
            return new SingleResult<C>(component);
        } catch (DispatchException e) {
            throw new ActionException(action, e);
        }
    }

    public C defineComponent(I id, CTX context) throws DispatchException {
        D detail = this.<ComponentDetailResult<D>>execute(context, newGetComponentDetailAction(id)).getValue();
        S state = this.<ComponentStateResult<S>>execute(context, newGetComponentStateAction(id)).getValue();
        return newComponent(id, detail, state);
    }

    protected <R extends Result> R execute(ExecutionContext context, Action<R> action) throws DispatchException {
        return context.getDispatcher().execute(action);
    }

    protected abstract Action<ComponentDetailResult<D>> newGetComponentDetailAction(I id);

    protected abstract Action<ComponentStateResult<S>> newGetComponentStateAction(I id);

    protected abstract C newComponent(I id, D detail, S state) throws DispatchException;
}
