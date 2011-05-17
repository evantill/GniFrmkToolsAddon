package com.gni.frmk.tools.addon.operation.action.component.root.scheduler;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.SchedulerState;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 13:57
 *
 * @author: e03229
 */
public class GetSchedulerState
        extends GetComponentState<SchedulerState, StringId> {
    public GetSchedulerState(StringId id) {
        super(id);
    }
}