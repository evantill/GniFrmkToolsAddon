package com.gni.frmk.tools.addon.operation.handler.component.root.scheduler;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.SchedulerState;
import com.gni.frmk.tools.addon.model.component.root.SchedulerStatus;
import com.gni.frmk.tools.addon.model.component.root.SuspendedStatus;
import com.gni.frmk.tools.addon.operation.action.component.root.scheduler.GetSchedulerState;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentStateHandler;
import com.gni.frmk.tools.addon.operation.result.SingleResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 14:02
 *
 * @author: e03229
 */
public class GetSchedulerStateHandler
        extends AbstractInvokeHandler<GetSchedulerState, SingleResult<SchedulerState>>
        implements GetComponentStateHandler<GetSchedulerState,StringId, SchedulerState, InvokeContext> {

    public GetSchedulerStateHandler() {
        super("wm.server.schedule:getUserTaskList");
    }

    @Override
    protected SingleResult<SchedulerState> parseOutput(GetSchedulerState action, IData output)
            throws ParseOutputException {
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
                        return new SingleResult<SchedulerState>(schedulerState);
                    } finally {
                        curDoc.destroy();
                    }
                }
            }
            return new SingleResult<SchedulerState>(new SchedulerState(SuspendedStatus.UNKNOWN, SchedulerStatus.UNKNONW));
        } finally {
            cur.destroy();
        }
    }


    private SchedulerState defineState(IDataCursor curDoc) {
        SuspendedStatus suspended = SuspendedStatus.valueOf(IDataUtil.getString(curDoc, "execState").toUpperCase());
        SchedulerStatus scheduled = SchedulerStatus.valueOf(IDataUtil.getString(curDoc, "schedState").toUpperCase());
        return new SchedulerState(suspended, scheduled);
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
