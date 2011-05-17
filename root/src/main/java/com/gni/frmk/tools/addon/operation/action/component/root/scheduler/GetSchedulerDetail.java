package com.gni.frmk.tools.addon.operation.action.component.root.scheduler;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.operation.action.component.ComponentAction;
import com.gni.frmk.tools.addon.model.component.root.Scheduler.SchedulerDetail;
import com.gni.frmk.tools.addon.operation.result.ComponentDetailResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 13:56
 *
 * @author: e03229
 */
public class GetSchedulerDetail
        implements ComponentAction<StringId,ComponentDetailResult<SchedulerDetail>> {

    private final StringId id;

    public GetSchedulerDetail(StringId id) {
        this.id = id;
    }

    public StringId getId() {
        return id;
    }
}