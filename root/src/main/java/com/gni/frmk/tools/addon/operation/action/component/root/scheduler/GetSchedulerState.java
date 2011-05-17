package com.gni.frmk.tools.addon.operation.action.component.root.scheduler;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.SchedulerState;
import com.gni.frmk.tools.addon.operation.action.component.ComponentAction;
import com.gni.frmk.tools.addon.operation.result.ComponentStateResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 13:57
 *
 * @author: e03229
 */
public class GetSchedulerState implements ComponentAction<StringId,ComponentStateResult<SchedulerState>> {

    private final StringId id;

    public GetSchedulerState(StringId id) {
        this.id = id;
    }

    public StringId getId() {
        return id;
    }
}