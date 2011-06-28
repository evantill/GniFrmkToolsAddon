package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.ComponentDetail;
import com.gni.frmk.tools.addon.model.component.ComponentId;
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
public class GetComponentDetail
        <I extends ComponentId<I>, D extends ComponentDetail<D>>
        implements Action<SingleResult<D>> {

    private final ComponentType<?, ?, I, ?, D> componentType;
    private final I componentId;

    private GetComponentDetail(ComponentType<?, ?, I, ?, D> componentType, I componentId) {
        this.componentType = componentType;
        this.componentId = componentId;
    }

    public ComponentType<?, ?, I, ?, D> getComponentType() {
        return componentType;
    }

    public I getComponentId() {
        return componentId;
    }

    public static <T extends ComponentType<T, ?, I, ?, D>,
            I extends ComponentId<I>,
            D extends ComponentDetail<D>> GetComponentDetail<I, D> newInstance(T type, I id) {
        return new GetComponentDetail<I, D>(type, id);
    }
}
