package com.gni.frmk.tools.addon.command.handler.wm.art;

import com.gni.frmk.tools.addon.command.action.wm.art.SuspendNotification;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.command.result.NoResult;
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
        super("wm.art.admin.notification:suspendPollingNotification");
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
                 in.getParameter()}
        });
    }
}
