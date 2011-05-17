package com.gni.frmk.tools.addon.operation.handler.component.root.scheduler;

import com.gni.frmk.tools.addon.operation.action.component.root.scheduler.SuspendUserTask;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.result.NoResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:19
 *
 * @author: e03229
 */
public class SuspendUserTaskHandler
        extends AbstractInvokeHandler<SuspendUserTask, NoResult>
        implements ActionHandler<SuspendUserTask, NoResult, InvokeContext> {

    public SuspendUserTaskHandler() {
        super("wm.server.schedule:suspendUserTask");
    }

    @Override
    public Class<SuspendUserTask> getActionType() {
        return SuspendUserTask.class;
    }

    @Override
    protected NoResult parseOutput(SuspendUserTask action, IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(SuspendUserTask in) {
        return IDataFactory.create(new Object[][]{
                {"oid",
                 in.getId().getValue()}
        });
    }
}
