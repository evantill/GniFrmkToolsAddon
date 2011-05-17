package com.gni.frmk.tools.addon.operation.handler.component.art.notification;

import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.action.component.art.notifications.DisableNotification;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.NoResult;
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
        super("pub.art.notification:disablePollingNotification");
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
                 in.getId().getName()}
        });
    }
}