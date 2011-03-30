package com.gni.frmk.tools.addon.invoke.handler.wmart;

import com.gni.frmk.tools.addon.dispatcher.NoResult;
import com.gni.frmk.tools.addon.invoke.action.wmart.DisableNotification;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.dispatcher.ActionHandler;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:21
 *
 * @author: e03229
 */
public class DisableNotificationHandler extends AbstractInvokeHandler<DisableNotification, NoResult>
        implements ActionHandler<DisableNotification, NoResult, InvokeContext> {

    public DisableNotificationHandler() {
        super("wm.art.admin.notification:disablePollingNotification");
    }

    @Override
    public Class<DisableNotification> getActionType() {
        return DisableNotification.class;
    }

    @Override
    protected NoResult parseOutput(DisableNotification action, IData output) {
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
