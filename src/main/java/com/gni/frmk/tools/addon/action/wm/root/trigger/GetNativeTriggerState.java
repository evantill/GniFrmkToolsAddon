package com.gni.frmk.tools.addon.action.wm.root.trigger;

import com.gni.frmk.tools.addon.model.NativeTriggerState;
import com.gni.frmk.tools.addon.model.StringId;
import com.gni.frmk.tools.addon.result.ComponentStateResult;
import ev.frmk.tools.plateform.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 14:31
 *
 * @author: e03229
 */
public class GetNativeTriggerState implements Action<ComponentStateResult<NativeTriggerState>> {
    private final StringId id;

    public GetNativeTriggerState(StringId id) {
        this.id = id;
    }

    public StringId getId() {
        return id;
    }
}
