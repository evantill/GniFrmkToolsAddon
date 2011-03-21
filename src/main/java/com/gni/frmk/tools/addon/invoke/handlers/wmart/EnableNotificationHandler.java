package com.gni.frmk.tools.addon.invoke.handlers.wmart;

import com.gni.frmk.tools.addon.dispatcher.NoResult;
import com.gni.frmk.tools.addon.dispatcher.ActionHandler;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.actions.wmart.EnableNotification;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:22
 *
 * @author: e03229
 */
public class EnableNotificationHandler extends AbstractInvokeHandler<EnableNotification, NoResult>
        implements ActionHandler<EnableNotification, NoResult, InvokeContext> {

    public EnableNotificationHandler() {
        super("wm.art.admin.notification:enablePollingNotification");
    }

    @Override
    public Class<EnableNotification> getActionType() {
        return EnableNotification.class;
    }

    @Override
    protected NoResult parseOutput(EnableNotification action, IData output) {
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
