package com.gni.frmk.tools.addon.operation.action;

import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/05/11
 * Time: 11:36
 *
 * @author: e03229
 */
public class GetComponentState<I extends ComponentId, S extends ComponentState>
        implements Action<SingleResult<S>> {
    private final ComponentType type;
    private final ComponentId id;

    public GetComponentState(ComponentType type, ComponentId id) {
        this.type = type;
        this.id = id;
    }

    public ComponentType getType() {
        return type;
    }

    public ComponentId getId() {
        return id;
    }
}
