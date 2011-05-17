package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.Component.Detail;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.model.component.Component.State;
import com.gni.frmk.tools.addon.operation.action.component.GetComponent;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;
import com.gni.frmk.tools.addon.operation.api.*;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 11:44
 *
 * @author: e03229
 */
public abstract class GetComponentHandler
        <I extends Id,
                D extends Detail,
                S extends State,
                C extends Component<I, S, D>,
                A extends GetComponent<C, I>,
                CTX extends ExecutionContext>
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
        D detail = this.execute(context, newGetComponentDetailAction(id)).getValue();
        S state = this.execute(context, newGetComponentStateAction(id)).getValue();
        return newComponent(id, detail, state);
    }

    protected <R extends Result> R execute(ExecutionContext context, Action<R> action) throws DispatchException {
        return context.getDispatcher().execute(action);
    }

    protected abstract GetComponentDetail<D, I> newGetComponentDetailAction(I id);

    protected abstract GetComponentState<S, I> newGetComponentStateAction(I id);

    protected abstract C newComponent(I id, D detail, S state) throws DispatchException;
}
