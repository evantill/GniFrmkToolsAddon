package com.gni.frmk.tools.addon.invoke.handlers.wmart;

import com.gni.frmk.tools.addon.invoke.ActionHandler;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.results.NoResult;
import com.gni.frmk.tools.addon.invoke.actions.wmart.SuspendNotification;
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
