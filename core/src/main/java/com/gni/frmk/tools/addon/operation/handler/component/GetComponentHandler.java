package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.Component.Detail;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.model.component.Component.State;
import com.gni.frmk.tools.addon.operation.action.component.ComponentFactory;
import com.gni.frmk.tools.addon.operation.action.component.GetComponent;
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

    private final ComponentFactory<I, S, D, C> factory;

    protected GetComponentHandler(ComponentFactory<I, S, D, C> factory) {
        this.factory = factory;
    }

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
        D detail = this.execute(context, factory.newGetComponentDetailAction(id)).getValue();
        S state = this.execute(context, factory.newGetComponentStateAction(id)).getValue();
        return factory.newComponent(id, detail, state);
    }

    protected <R extends Result> R execute(ExecutionContext context, Action<R> action) throws DispatchException {
        return context.getDispatcher().execute(action);
    }
}
