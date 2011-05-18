package com.gni.frmk.tools.addon.operation.handler.component.art.notification;

import com.gni.frmk.tools.addon.operation.action.component.art.notifications.EnableAdapterNotification;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.result.NoResult;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:22
 *
 * @author: e03229
 */
public class EnableAdapterNotificationHandler extends AbstractInvokeHandler<EnableAdapterNotification, NoResult>
        implements ActionHandler<EnableAdapterNotification, NoResult, InvokeContext> {

    public EnableAdapterNotificationHandler() {
        super("pub.art.notification:enablePollingNotification");
    }

    @Override
    public Class<EnableAdapterNotification> getActionType() {
        return EnableAdapterNotification.class;
    }

    @Override
    protected NoResult parseOutput(EnableAdapterNotification action, IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(EnableAdapterNotification in) {
        return IDataFactory.create(new Object[][]{
                {"notificationName",
                 in.getId().getName()}
        });
    }
}
