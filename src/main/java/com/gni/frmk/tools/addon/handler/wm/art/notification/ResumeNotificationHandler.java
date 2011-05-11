package com.gni.frmk.tools.addon.handler.wm.art.notification;

import com.gni.frmk.tools.addon.action.wm.art.notifications.ResumeNotification;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.result.NoResult;
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
        super("pub.art.notification:resumePollingNotification");
    }

    @Override
    public Class<ResumeNotification> getActionType() {
        return ResumeNotification.class;
    }

    @Override
    protected IData prepareInput(ResumeNotification in) {
        return IDataFactory.create(new Object[][]{
                {"notificationName",
                 in.getId().getName()}
        });
    }

    @Override
    protected NoResult parseOutput(ResumeNotification action, IData output) {
        return NoResult.newInstance();
    }
}
