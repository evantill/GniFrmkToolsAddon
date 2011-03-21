package com.gni.frmk.tools.addon.invoke.handlers.wmroot;

import com.gni.frmk.tools.addon.dispatcher.NoResult;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.SuspendUserTask;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.dispatcher.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:19
 *
 * @author: e03229
 */
public class SuspendUserTaskHandler extends AbstractInvokeHandler<SuspendUserTask, NoResult>
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
                 in.getParameter()}
        });
    }
}
