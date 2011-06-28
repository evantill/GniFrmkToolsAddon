package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentDetail;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.ListResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 14:37
 *
 * @author: e03229
 */
public class GetComponentsByType
        <T extends ComponentType<T, C, I, S, D>,
                C extends Component<C, T, I, S, D>,
                I extends ComponentId<I>,
                S extends ComponentState<S>,
                D extends ComponentDetail<D>>
        implements Action<ListResult<C>> {

    protected T componentType;

    public GetComponentsByType(T componentType) {
        this.componentType = componentType;
    }

    public T getComponentType() {
        return componentType;
    }

    public static <T extends ComponentType<T, C, I, S, D>,
            C extends Component<C, T, I, S, D>,
            I extends ComponentId<I>,
            S extends ComponentState<S>,
            D extends ComponentDetail<D>>
    GetComponentsByType<T, C, I, S, D> createAction(T componentType) {
        return new GetComponentsByType<T, C, I, S, D>(componentType);
    }
}
