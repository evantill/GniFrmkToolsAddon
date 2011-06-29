package com.gni.frmk.tools.addon.operation.handler.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentDetail;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.model.component.base.BaseComponent.Builder;
import com.gni.frmk.tools.addon.operation.action.component.GetComponent;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.operation.api.Dispatcher;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

import javax.enterprise.util.TypeLiteral;

/**
 * Created by IntelliJ IDEA.
 * Date: 15/06/11
 * Time: 13:17
 *
 * @author: e03229
 */
public class GetComponentHandler
        implements ActionHandler<GetComponent<?, ?, ?, ?, ?>, SingleResult<? extends Component<?, ?, ?, ?, ?>>, InvokeContext> {

    private static final TypeLiteral<GetComponent<?, ?, ?, ?, ?>> TYPE_LITERAL = new TypeLiteral<GetComponent<?, ?, ?, ?, ?>>() {
    };

    @Override
    public TypeLiteral<GetComponent<?, ?, ?, ?, ?>> getActionType() {
        return TYPE_LITERAL;
    }

    @Override
    public SingleResult<? extends Component<?, ?, ?, ?, ?>> execute(GetComponent<?, ?, ?, ?, ?> action, InvokeContext context) throws ActionException {
        return this.executeTypeSafe(action, context);
    }

    public <T extends ComponentType<T, C, I, S, D>,
            C extends Component<C, T, I, S, D>,
            I extends ComponentId<I>,
            D extends ComponentDetail<D>,
            S extends ComponentState<S>>
    SingleResult<C> executeTypeSafe(GetComponent<T, C, I, S, D> action, InvokeContext context) throws ActionException {
        Dispatcher dispatcher = context.getDispatcher();
        I requestId = action.getComponentId();
        T requestType = action.getComponentType();
        Builder<?, C, T, I, S, D> builder = action.getComponentType()
                                                  .componentBuilder()
                                                  .id(requestId);
        try {
            GetComponentDetail<I, D> detailAction = GetComponentDetail.newInstance(requestType, requestId);
            D componentDetail = dispatcher.execute(detailAction).getValue();
            builder.detail(componentDetail);
        } catch (DispatchException cause) {
            throw new ActionException(action, cause);
        }
        try {
            GetComponentState<I, S> stateAction = GetComponentState.newInstance(requestType, requestId);
            S componentState = dispatcher.execute(stateAction).getValue();
            builder.state(componentState);
        } catch (DispatchException cause) {
            throw new ActionException(action, cause);
        }
        return SingleResult.newInstance(builder.validate().build());
    }
}
