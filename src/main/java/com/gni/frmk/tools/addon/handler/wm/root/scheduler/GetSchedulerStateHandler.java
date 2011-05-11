package com.gni.frmk.tools.addon.handler.wm.root.scheduler;

import com.gni.frmk.tools.addon.action.wm.root.scheduler.GetSchedulerState;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState.SchedulerStatus;
import com.gni.frmk.tools.addon.result.ComponentStateResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.api.action.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 14:02
 *
 * @author: e03229
 */
public class GetSchedulerStateHandler
        extends AbstractInvokeHandler<GetSchedulerState, ComponentStateResult<SchedulerState>>
        implements ActionHandler<GetSchedulerState, ComponentStateResult<SchedulerState>, InvokeContext> {

    public GetSchedulerStateHandler() {
        super("wm.server.schedule:getUserTaskList");
    }

    @Override
    protected ComponentStateResult<SchedulerState> parseOutput(GetSchedulerState action, IData output) throws ParseOutputException {
        IDataCursor cur = output.getCursor();
        try {
            IData[] tasksDatas = IDataUtil.getIDataArray(cur, "tasks");
            String oidToFind = action.getId().getValue();
            if (tasksDatas != null) {
                for (IData tasks : tasksDatas) {
                    IDataCursor curDoc = tasks.getCursor();
                    try {
                        String oid = IDataUtil.getString(curDoc, "oid");
                        if (!oidToFind.equals(oid)) {
                            continue;
                        }
                        SchedulerState schedulerState = defineState(curDoc);
                        return new ComponentStateResult<SchedulerState>(schedulerState);
                    } finally {
                        curDoc.destroy();
                    }
                }
            }
            return new ComponentStateResult<SchedulerState>(new SchedulerState(EnableStatus.UNKNOWN, SchedulerStatus.UNKNONW));
        } finally {
            cur.destroy();
        }
    }

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

    private SchedulerState defineState(IDataCursor curDoc) {
        EnableStatus enabled = SuspendedState.valueOf(IDataUtil.getString(curDoc, "execState").toUpperCase())
                                             .toEnableState();
        SchedulerStatus scheduled = SchedulerStatus.valueOf(IDataUtil.getString(curDoc, "schedState").toUpperCase());
        return new SchedulerState(enabled, scheduled);
    }

    @Override
    protected IData prepareInput(GetSchedulerState action) throws ParseInputException {
        return EMPTY_INPUT;
    }

    @Override
    public Class<GetSchedulerState> getActionType() {
        return GetSchedulerState.class;
    }

}
