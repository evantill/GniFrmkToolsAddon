package com.gni.frmk.tools.addon.invoke.handler.wmroot;

import com.gni.frmk.tools.addon.configuration.component.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.configuration.component.Scheduler;
import com.gni.frmk.tools.addon.configuration.component.SchedulerState;
import com.gni.frmk.tools.addon.configuration.component.SchedulerState.SchedulerStatus;
import com.gni.frmk.tools.addon.dispatcher.ActionHandler;
import com.gni.frmk.tools.addon.dispatcher.ListResult;
import com.gni.frmk.tools.addon.invoke.action.wmroot.GetUserTaskList;
import com.gni.frmk.tools.addon.invoke.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.google.common.collect.Lists;
import com.wm.data.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 14:34
 *
 * @author: e03229
 */
public class GetUserTaskListHandler extends AbstractInvokeHandler<GetUserTaskList, ListResult<Scheduler>>
        implements ActionHandler<GetUserTaskList, ListResult<Scheduler>, InvokeContext> {

    public GetUserTaskListHandler() {
        super("wm.server.schedule:getUserTaskList");
    }

    @Override
    protected ListResult<Scheduler> parseOutput(GetUserTaskList action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            List<Scheduler> values = Lists.newArrayList();
            IData[] tasksDatas = IDataUtil.getIDataArray(cur, "tasks");
            if (tasksDatas != null) {
                for (IData tasks : tasksDatas) {
                    IDataCursor curDoc = tasks.getCursor();
                    try {
                        values.add(Scheduler.builder()
                                            .name(IDataUtil.getString(curDoc, "name"))
                                            .oid(IDataUtil.getString(curDoc, "oid"))
                                            .schedulerType(IDataUtil.getString(curDoc, "type"))
                                            .service(IDataUtil.getString(curDoc, "service"))
                                            .description(IDataUtil.getString(curDoc, "description"))
                                            .defineState(defineState(curDoc))
                                            .build());
                    } finally {
                        curDoc.destroy();
                    }
                }
            }
            return new ListResult<Scheduler>(values);
        } finally {
            cur.destroy();
        }
    }

    private SchedulerState defineState(IDataCursor curDoc) {
        EnableStatus enabled = EnableStatus.valueOf(IDataUtil.getString(curDoc, "execState").toUpperCase());
        SchedulerStatus scheduled = SchedulerStatus.valueOf(IDataUtil.getString(curDoc, "schedState").toUpperCase());
        return new SchedulerState(enabled, scheduled);
    }

    @Override
    protected IData prepareInput(GetUserTaskList in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<GetUserTaskList> getActionType() {
        return GetUserTaskList.class;
    }

}