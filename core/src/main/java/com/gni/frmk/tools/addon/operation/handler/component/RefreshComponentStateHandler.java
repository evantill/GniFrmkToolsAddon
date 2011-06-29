package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;
import com.gni.frmk.tools.addon.operation.action.component.RefreshComponentState;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

import javax.enterprise.util.TypeLiteral;

/**
 * Created by IntelliJ IDEA.
 * Date: 29/06/11
 * Time: 16:41
 *
 * @author: e03229
 */
public class RefreshComponentStateHandler
        implements ActionHandler<RefreshComponentState<?, ?, ?>, SingleResult<? extends Component>, InvokeContext> {

    private static final TypeLiteral<RefreshComponentState<?, ?, ?>> TYPE_LITERAL = new TypeLiteral<RefreshComponentState<?, ?, ?>>() {
    };

    @Override
    public TypeLiteral<RefreshComponentState<?, ?, ?>> getActionType() {
        return TYPE_LITERAL;
    }

    @Override
    public SingleResult<? extends Component> execute(RefreshComponentState<?, ?, ?> action, InvokeContext context) throws ActionException {
        return executeTypeSafe(action, context);
    }

    public <C extends Component<C, ?, I, S, ?>,
            I extends ComponentId<I>,
            S extends ComponentState<S>>
    SingleResult<C> executeTypeSafe(RefreshComponentState<C, I, S> action, InvokeContext context) throws ActionException {
        C component = action.getComponent();
        S oldState = component.getCurrentState();
        S newState = context.getDispatcher()
                            .executeFromAction(action, GetComponentState.newInstance(component))
                            .getValue();
        if (oldState.equals(newState)) {
            return SingleResult.newInstance(component);
        } else {
            C newComponent = component.getType().componentBuilder().from(component).state(newState).validate().build();
            return SingleResult.newInstance(newComponent);
        }
    }


}
