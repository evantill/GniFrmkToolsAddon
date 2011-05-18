package com.gni.frmk.tools.addon.operation.handler.component.art.notification;

import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.action.component.art.notifications.DisableAdapterNotification;
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
public class DisableAdapterNotificationHandler extends AbstractInvokeHandler<DisableAdapterNotification, NoResult>
        implements ActionHandler<DisableAdapterNotification, NoResult, InvokeContext> {

    public DisableAdapterNotificationHandler() {
        super("pub.art.notification:disablePollingNotification");
    }

    @Override
    public Class<DisableAdapterNotification> getActionType() {
        return DisableAdapterNotification.class;
    }

    @Override
    protected NoResult parseOutput(DisableAdapterNotification action, IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(DisableAdapterNotification in) {
        return IDataFactory.create(new Object[][]{
                {"notificationName",
                 in.getId().getName()}
        });
    }
}
