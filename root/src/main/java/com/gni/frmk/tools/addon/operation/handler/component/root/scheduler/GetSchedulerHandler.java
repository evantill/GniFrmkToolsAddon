package com.gni.frmk.tools.addon.operation.handler.component.root.scheduler;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.Scheduler;
import com.gni.frmk.tools.addon.model.component.root.Scheduler.SchedulerDetail;
import com.gni.frmk.tools.addon.model.component.root.SchedulerState;
import com.gni.frmk.tools.addon.operation.action.component.root.scheduler.GetScheduler;
import com.gni.frmk.tools.addon.operation.action.component.root.scheduler.SchedulerFactory;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 10:37
 *
 * @author: e03229
 */
public class GetSchedulerHandler
        extends GetComponentHandler<StringId, SchedulerDetail, SchedulerState, Scheduler, GetScheduler, InvokeContext> {

    public GetSchedulerHandler() {
        super(new SchedulerFactory());
    }

    @Override
    public Class<? extends GetScheduler> getActionType() {
        return GetScheduler.class;
    }
}
