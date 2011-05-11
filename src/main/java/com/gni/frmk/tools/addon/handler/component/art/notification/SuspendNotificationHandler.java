package com.gni.frmk.tools.addon.handler.component.art.notification;

import com.gni.frmk.tools.addon.action.component.art.notifications.SuspendNotification;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.result.NoResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:24
 *
 * @author: e03229
 */
public class SuspendNotificationHandler extends AbstractInvokeHandler<SuspendNotification, NoResult>
        implements ActionHandler<SuspendNotification, NoResult, InvokeContext> {

    public SuspendNotificationHandler() {
        super("pub.art.notification:suspendPollingNotification");
    }

    @Override
    public Class<SuspendNotification> getActionType() {
        return SuspendNotification.class;
    }

    @Override
    protected NoResult parseOutput(SuspendNotification action,IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(SuspendNotification in) {
        return IDataFactory.create(new Object[][]{
                {"notificationName",
                 in.getId().getName()}
        });
    }
}
