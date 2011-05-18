package com.gni.frmk.tools.addon.operation.handler.component.art.notification;

import com.gni.frmk.tools.addon.operation.action.component.art.notifications.ResumeAdapterNotification;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.result.NoResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:23
 *
 * @author: e03229
 */
public class ResumeAdapterNotificationHandler extends AbstractInvokeHandler<ResumeAdapterNotification, NoResult>
        implements ActionHandler<ResumeAdapterNotification, NoResult, InvokeContext> {

    public ResumeAdapterNotificationHandler() {
        super("pub.art.notification:resumePollingNotification");
    }

    @Override
    public Class<ResumeAdapterNotification> getActionType() {
        return ResumeAdapterNotification.class;
    }

    @Override
    protected IData prepareInput(ResumeAdapterNotification in) {
        return IDataFactory.create(new Object[][]{
                {"notificationName",
                 in.getId().getName()}
        });
    }

    @Override
    protected NoResult parseOutput(ResumeAdapterNotification action, IData output) {
        return NoResult.newInstance();
    }
}
