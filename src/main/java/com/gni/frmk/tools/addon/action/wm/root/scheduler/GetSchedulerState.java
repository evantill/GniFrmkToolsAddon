package com.gni.frmk.tools.addon.action.wm.root.scheduler;

import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.result.ComponentStateResult;
import com.gni.frmk.tools.addon.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 13:57
 *
 * @author: e03229
 */
public class GetSchedulerState implements Action<ComponentStateResult<SchedulerState>> {

    private final StringId id;

    public GetSchedulerState(StringId id) {
        this.id = id;
    }

    public StringId getId() {
        return id;
    }
}