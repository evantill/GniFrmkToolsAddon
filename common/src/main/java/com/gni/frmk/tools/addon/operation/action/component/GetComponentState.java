package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.model.component.Component.State;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 14:34
 *
 * @author: e03229
 */
public abstract class GetComponentState<S extends State, I extends Id>
        implements Action<SingleResult<S>> {
    private final I id;

    protected GetComponentState(I id) {
        this.id = id;
    }

    public I getId() {
        return id;
    }
}
