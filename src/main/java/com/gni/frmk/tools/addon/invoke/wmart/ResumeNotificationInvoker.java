package com.gni.frmk.tools.addon.invoke.wmart;

import com.gni.frmk.tools.addon.invoke.NoResult;
import com.gni.frmk.tools.addon.invoke.AbstractInvoker;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:23
 *
 * @author: e03229
 */
 class ResumeNotificationInvoker extends AbstractInvoker<ResumeNotification, NoResult>
        implements ActionHandler<ResumeNotification, NoResult, InvokeContext> {

    public ResumeNotificationInvoker() {
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
    protected NoResult parseOutput(IData output) {
        return NoResult.newInstance();
    }
}
