package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.model.component.base.BaseComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SetResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 11:33
 *
 * @author: e03229
 */
public class ListComponentIds<I extends ComponentId<?>>
        implements Action<SetResult<I>> {

    private final ComponentType<?, ?, I, ?, ?> type;

    public ListComponentIds(ComponentType<?, ?, I, ?, ?> type) {
        this.type = type;
    }

    public ComponentType<?, ?, I, ?, ?> getType() {
        return type;
    }

    public static <T extends ComponentType<?, ?, I, ?, ?>, I extends ComponentId<?>> ListComponentIds<I> build(T type) {
        return new ListComponentIds<I>(type);
    }

}
