package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentDetail;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 14:37
 *
 * @author: e03229
 */
public class GetComponent
        <T extends ComponentType<T, C, I, S, D>,
                C extends Component<C, T, I, S, D>,
                I extends ComponentId<I>,
                S extends ComponentState<S>,
                D extends ComponentDetail<D>>
        implements Action<SingleResult<C>> {

    protected T componentType;
    protected I componentId;

    public GetComponent(T componentType, I componentId) {
        this.componentType = componentType;
        this.componentId = componentId;
    }

    public T getComponentType() {
        return componentType;
    }

    public I getComponentId() {
        return componentId;
    }

    public static <T extends ComponentType<T, C, I, S, D>,
            C extends Component<C, T, I, S, D>,
            I extends ComponentId<I>,
            S extends ComponentState<S>,
            D extends ComponentDetail<D>>
    GetComponent<T, C, I, S, D> newInstance(T type, I id) {
        return new GetComponent<T, C, I, S, D>(type, id);
    }
}
