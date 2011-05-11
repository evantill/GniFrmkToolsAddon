package com.gni.frmk.tools.addon.action.wm.root.scheduler;

import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.Scheduler.Detail;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.gni.frmk.tools.addon.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 13:56
 *
 * @author: e03229
 */
public class GetSchedulerDetail
        implements Action<ComponentDetailResult<Detail>> {

    private final StringId id;

    public GetSchedulerDetail(StringId id) {
        this.id = id;
    }

    public StringId getId() {
        return id;
    }
}