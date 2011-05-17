package com.gni.frmk.tools.addon.operation.action.component.root.trigger;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.NativeTriggerState;
import com.gni.frmk.tools.addon.operation.action.component.ComponentAction;
import com.gni.frmk.tools.addon.operation.result.ComponentStateResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 14:31
 *
 * @author: e03229
 */
public class GetNativeTriggerState implements ComponentAction<StringId,ComponentStateResult<NativeTriggerState>> {
    private final StringId id;

    public GetNativeTriggerState(StringId id) {
        this.id = id;
    }

    public StringId getId() {
        return id;
    }
}
