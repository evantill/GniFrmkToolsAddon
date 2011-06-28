package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 14:31
 *
 * @author: e03229
 */
public class GetComponentState
        <I extends ComponentId<?>, S extends ComponentState<?>>
        implements Action<SingleResult<S>> {

    private final ComponentType<?, ?, I, S, ?> componentType;
    private final I componentId;

    private GetComponentState(ComponentType<?, ?, I, S, ?> componentType, I componentId) {
        this.componentType = componentType;
        this.componentId = componentId;
    }

    public ComponentType<?, ?, I, S, ?> getComponentType() {
        return componentType;
    }

    public I getComponentId() {
        return componentId;
    }

    public static <T extends ComponentType<?, ?, I, S, ?>,
            I extends ComponentId<I>,
            S extends ComponentState<S>>
    GetComponentState<I, S> newInstance(T type, I id) {
        return new GetComponentState<I, S>(type, id);
    }

    public static <
            C extends Component<C, T, I, S, ?>,
            T extends ComponentType<T, C, I, S, ?>,
            I extends ComponentId<?>,
            S extends ComponentState<?>>
    GetComponentState<I, S> newInstance(C component) {
        return new GetComponentState<I, S>(component.getType(), component.getId());
    }
}
