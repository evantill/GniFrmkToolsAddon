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
public class ChangeComponentState
        <I extends ComponentId<?>, S extends ComponentState<?>>
        implements Action<SingleResult<S>> {

    private final ComponentType<?, ?, I, S, ?> componentType;
    private final I componentId;
    private final S oldState;
    private final S newState;

    public <C extends Component<?, ?, I, S, ?>> ChangeComponentState(C component, S newState) {
        this.componentType = component.getType();
        this.componentId = component.getId();
        this.oldState = component.getCurrentState();
        this.newState = newState;
    }

    public ComponentType<?, ?, I, S, ?> getComponentType() {
        return componentType;
    }

    public I getComponentId() {
        return componentId;
    }

    public S getOldState() {
        return oldState;
    }

    public S getNewState() {
        return newState;
    }

    public static <T extends ComponentType<T, ?, I, S, ?>,
            C extends Component<?, T, I, S, ?>,
            I extends ComponentId<?>,
            S extends ComponentState<?>>
    ChangeComponentState<I, S> newInstance(C component, S newState) {
        return new ChangeComponentState<I, S>(component, newState);
    }
}
