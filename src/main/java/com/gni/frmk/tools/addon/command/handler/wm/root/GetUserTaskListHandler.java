package com.gni.frmk.tools.addon.command.handler.wm.root;

import com.gni.frmk.tools.addon.command.action.wm.root.GetUserTaskList;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.command.result.ListResult;
import com.gni.frmk.tools.addon.model.component.Scheduler;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState.SchedulerStatus;
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

    private enum SuspendedState {
        SUSPENDED {
            @Override
            public EnableStatus toEnableState() {
                return EnableStatus.DISABLED;
            }
        }, READY {
            @Override
            public EnableStatus toEnableState() {
                return  EnableStatus.ENABLED;
            }
        };

        public abstract EnableStatus toEnableState();
    }

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
        EnableStatus enabled = SuspendedState.valueOf(IDataUtil.getString(curDoc, "execState").toUpperCase()).toEnableState();
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