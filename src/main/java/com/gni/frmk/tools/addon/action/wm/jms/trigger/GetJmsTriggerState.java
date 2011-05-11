package com.gni.frmk.tools.addon.action.wm.jms.trigger;

import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.result.ComponentStateResult;
import com.gni.frmk.tools.addon.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 15:56
 *
 * @author: e03229
 */
public class GetJmsTriggerState
        implements Action<ComponentStateResult<ActivableState>> {
    private final StringId id;

    public GetJmsTriggerState(StringId id) {
        this.id = id;
    }

    public StringId getId() {
        return id;
    }
}
