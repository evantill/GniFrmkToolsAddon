package com.gni.frmk.tools.addon.invoke.handlers.wmart;

import com.gni.frmk.tools.addon.dispatcher.NoResult;
import com.gni.frmk.tools.addon.invoke.actions.wmart.ResumeNotification;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.dispatcher.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:23
 *
 * @author: e03229
 */
public class ResumeNotificationHandler extends AbstractInvokeHandler<ResumeNotification, NoResult>
        implements ActionHandler<ResumeNotification, NoResult, InvokeContext> {

    public ResumeNotificationHandler() {
        super("wm.art.admin.notification:resumePollingNotification");
    }

    @Override
    public Class<ResumeNotification> getActionType() {
        return ResumeNotification.class;
    }

    @Override
    protected IData prepareInput(ResumeNotification in) {
        return IDataFactory.create(new Object[][]{
                {"notificationName",
                 in.getParameter()}
        });
    }

    @Override
    protected NoResult parseOutput(ResumeNotification action, IData output) {
        return NoResult.newInstance();
    }
}
