package com.gni.frmk.tools.addon.operation.action.component.root.scheduler;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.Scheduler.SchedulerDetail;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 13:56
 *
 * @author: e03229
 */
public class GetSchedulerDetail
        extends GetComponentDetail<SchedulerDetail, StringId> {
    public GetSchedulerDetail(StringId id) {
        super(id);
    }
}