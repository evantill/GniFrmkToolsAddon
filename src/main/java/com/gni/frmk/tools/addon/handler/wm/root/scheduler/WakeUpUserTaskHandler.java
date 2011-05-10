package com.gni.frmk.tools.addon.handler.wm.root.scheduler;

import com.gni.frmk.tools.addon.action.wm.root.scheduler.WakeUpUserTask;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.result.NoResult;
import com.wm.data.*;
import ev.frmk.tools.plateform.api.action.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:19
 *
 * @author: e03229
 */
public class WakeUpUserTaskHandler
        extends AbstractInvokeHandler<WakeUpUserTask, NoResult>
        implements ActionHandler<WakeUpUserTask, NoResult, InvokeContext> {

    public WakeUpUserTaskHandler() {
        super("wm.server.schedule:wakeupUserTask");
    }

    @Override
    public Class<WakeUpUserTask> getActionType() {
        return WakeUpUserTask.class;
    }

    @Override
    protected NoResult parseOutput(WakeUpUserTask
            action, IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(WakeUpUserTask
            in) {
        return IDataFactory.create(new Object[][]{
                {"oid",
                 in.getOid().getValue()}
        });
    }
}