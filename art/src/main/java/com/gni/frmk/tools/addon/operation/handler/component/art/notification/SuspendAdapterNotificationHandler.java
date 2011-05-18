package com.gni.frmk.tools.addon.operation.handler.component.art.notification;

import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.action.component.art.notifications.SuspendAdapterNotification;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.NoResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:24
 *
 * @author: e03229
 */
public class SuspendAdapterNotificationHandler extends AbstractInvokeHandler<SuspendAdapterNotification, NoResult>
        implements ActionHandler<SuspendAdapterNotification, NoResult, InvokeContext> {

    public SuspendAdapterNotificationHandler() {
        super("pub.art.notification:suspendPollingNotification");
    }

    @Override
    public Class<SuspendAdapterNotification> getActionType() {
        return SuspendAdapterNotification.class;
    }

    @Override
    protected NoResult parseOutput(SuspendAdapterNotification action,IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(SuspendAdapterNotification in) {
        return IDataFactory.create(new Object[][]{
                {"notificationName",
                 in.getId().getName()}
        });
    }
}
