package com.gni.frmk.tools.addon.invoke.handlers.wmart;

import com.gni.frmk.tools.addon.invoke.handlers.AbstractHandler;
import com.gni.frmk.tools.addon.invoke.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.results.NoResult;
import com.gni.frmk.tools.addon.invoke.actions.wmart.ResumeNotification;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:23
 *
 * @author: e03229
 */
public class ResumeNotificationHandler extends AbstractHandler<ResumeNotification, NoResult>
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
    protected NoResult parseOutput(IData output) {
        return NoResult.newInstance();
    }
}
