package com.gni.frmk.tools.addon.invoke.wmart;

import com.gni.frmk.tools.addon.invoke.NoResult;
import com.gni.frmk.tools.addon.invoke.AbstractInvoker;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:24
 *
 * @author: e03229
 */
 class SuspendNotificationInvoker extends AbstractInvoker<SuspendNotification, NoResult>
        implements ActionHandler<SuspendNotification, NoResult, InvokeContext> {

    public SuspendNotificationInvoker() {
        super("wm.art.admin.notification:suspendPollingNotification");
    }

    @Override
    public Class<SuspendNotification> getActionType() {
        return SuspendNotification.class;
    }

    @Override
    protected NoResult parseOutput(IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(SuspendNotification in) {
        return IDataFactory.create(new Object[][]{
                {"notificationName",
                 in.getParameter()}
        });
    }
}
