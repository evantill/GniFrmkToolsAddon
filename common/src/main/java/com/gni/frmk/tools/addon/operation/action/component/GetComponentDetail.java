package com.gni.frmk.tools.addon.operation.action.component;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.model.component.Component.Detail;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 14:31
 *
 * @author: e03229
 */
public abstract class GetComponentDetail<D extends Detail, I extends Id>
        implements Action<SingleResult<D>> {
    private final I id;

    protected GetComponentDetail(I id) {
        this.id = id;
    }

    public I getId() {
        return id;
    }
}
