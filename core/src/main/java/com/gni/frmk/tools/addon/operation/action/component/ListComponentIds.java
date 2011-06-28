package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SetResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 11:33
 *
 * @author: e03229
 */
public class ListComponentIds<I extends ComponentId<I>>
        implements Action<SetResult<I>> {

    private final ComponentType<?, ?, I, ?, ?> componentType;

    public ListComponentIds(ComponentType<?, ?, I, ?, ?> componentType) {
        this.componentType = componentType;
    }

    public ComponentType<?, ?, I, ?, ?> getComponentType() {
        return componentType;
    }

    public static <I extends ComponentId<I>, T extends ComponentType<T, ?, I, ?, ?>> ListComponentIds<I> build(T type) {
        return new ListComponentIds<I>(type);
    }
}
