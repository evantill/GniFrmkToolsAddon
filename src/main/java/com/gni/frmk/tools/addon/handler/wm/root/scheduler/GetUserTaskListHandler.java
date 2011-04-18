package com.gni.frmk.tools.addon.handler.wm.root.scheduler;

import com.gni.frmk.tools.addon.action.wm.root.scheduler.GetUserTaskList;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.ImmutableScheduler.MutableScheduler;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState.SchedulerStatus;
import com.gni.frmk.tools.addon.result.ListResult;
import com.google.common.collect.Maps;
import com.wm.data.*;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 14:34
 *
 * @author: e03229
 */
public class GetUserTaskListHandler extends AbstractInvokeHandler<GetUserTaskList, ListResult<MutableScheduler>>
        implements ActionHandler<GetUserTaskList, ListResult<MutableScheduler>, InvokeContext> {

    private enum SuspendedState {
        SUSPENDED {
            @Override
            public EnableStatus toEnableState() {
                return EnableStatus.DISABLED;
            }
        }, READY {
            @Override
            public EnableStatus toEnableState() {
                return EnableStatus.ENABLED;
            }
        };

        public abstract EnableStatus toEnableState();
    }

    public GetUserTaskListHandler() {
        super("wm.server.schedule:getUserTaskList");
    }

    @Override
    protected ListResult<MutableScheduler> parseOutput(GetUserTaskList action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            Map<String, MutableScheduler> values = Maps.newHashMap();
            for (MutableScheduler value : action.getCollection()) {
                values.put(value.getComponentId().asString(), value);
            }
            IData[] tasksDatas = IDataUtil.getIDataArray(cur, "tasks");
            if (tasksDatas != null) {
                for (IData tasks : tasksDatas) {
                    IDataCursor curDoc = tasks.getCursor();
                    try {
                        String oid = IDataUtil.getString(curDoc, "oid");
                        MutableScheduler value = values.get(oid);
                        if (value == null && action.isUpdate()) {
                            continue;
                        } else if (value == null && !action.isUpdate()) {
                            value = new MutableScheduler();
                            value.setOid(oid);
                            value.setName(IDataUtil.getString(curDoc, "name"));
                            value.setSchedulerType(IDataUtil.getString(curDoc, "type"));
                            value.setService(IDataUtil.getString(curDoc, "service"));
                            value.setDescription(IDataUtil.getString(curDoc, "description"));

                        }

                        value.setState(defineState(curDoc));
                        values.put(value.getComponentId().asString(), value);
                    } finally {
                        curDoc.destroy();
                    }
                }
            }
            return new ListResult<MutableScheduler>(values.values());
        } finally {
            cur.destroy();
        }
    }

    private SchedulerState defineState(IDataCursor curDoc) {
        EnableStatus enabled = SuspendedState.valueOf(IDataUtil.getString(curDoc, "execState").toUpperCase())
                                             .toEnableState();
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