package com.gni.frmk.tools.addon.invoke.wmart;

import com.gni.frmk.tools.addon.invoke.AbstractInvoker;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.NoResult;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionHandler;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:22
 *
 * @author: e03229
 */
 class EnableNotificationInvoker extends AbstractInvoker<EnableNotification, NoResult>
        implements ActionHandler<EnableNotification, NoResult, InvokeContext> {

    public EnableNotificationInvoker() {
        super("wm.art.admin.notification:enablePollingNotification");
    }

    @Override
    public Class<EnableNotification> getActionType() {
        return EnableNotification.class;
    }

    @Override
    protected NoResult parseOutput(IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(EnableNotification in) {
        return IDataFactory.create(new Object[][]{
                {"notificationName",
                 in.getParameter()}
        });
    }
}
