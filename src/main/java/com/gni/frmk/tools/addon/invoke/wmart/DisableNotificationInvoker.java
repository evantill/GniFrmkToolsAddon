package com.gni.frmk.tools.addon.invoke.wmart;

import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.NoResult;
import com.gni.frmk.tools.addon.invoke.AbstractInvoker;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionHandler;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:21
 *
 * @author: e03229
 */
 class DisableNotificationInvoker extends AbstractInvoker<DisableNotification, NoResult>
        implements ActionHandler<DisableNotification, NoResult, InvokeContext> {

    public DisableNotificationInvoker() {
        super("wm.art.admin.notification:disablePollingNotification");
    }

    @Override
    public Class<DisableNotification> getActionType() {
        return DisableNotification.class;
    }

    @Override
    protected NoResult parseOutput(IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(DisableNotification in) {
        return IDataFactory.create(new Object[][]{
                {"notificationName",
                 in.getParameter()}
        });
    }
}
