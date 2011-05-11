package com.gni.frmk.tools.addon.handler.component.art.notification;

import com.gni.frmk.tools.addon.action.component.art.notifications.EnableNotification;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.result.NoResult;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
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
        super("pub.art.notification:enablePollingNotification");
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
                 in.getId().getName()}
        });
    }
}
