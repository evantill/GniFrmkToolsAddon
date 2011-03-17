package com.gni.frmk.tools.addon.invoke.handlers.wmroot;

import com.gni.frmk.tools.addon.invoke.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.WakeUpUserTask;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractHandler;
import com.gni.frmk.tools.addon.invoke.results.NoResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:19
 *
 * @author: e03229
 */
public class WakeUpUserTaskHandler extends AbstractHandler<WakeUpUserTask, NoResult>
        implements ActionHandler<WakeUpUserTask, NoResult, InvokeContext> {

    public WakeUpUserTaskHandler() {
        super("wm.server.schedule:wakeupUserTask");
    }

    @Override
    public Class<WakeUpUserTask> getActionType() {
        return WakeUpUserTask.class;
    }

    @Override
    protected NoResult parseOutput(IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(WakeUpUserTask in) {
        return IDataFactory.create(new Object[][]{
                {"oid",
                 in.getParameter()}
        });
    }
}
