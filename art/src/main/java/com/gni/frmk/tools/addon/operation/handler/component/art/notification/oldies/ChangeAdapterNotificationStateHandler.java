package com.gni.frmk.tools.addon.operation.handler.component.art.notification.oldies;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.notifications.oldies.ChangeAdapterNotificationState;
import com.gni.frmk.tools.addon.operation.action.component.art.notifications.oldies.DisableAdapterNotification;
import com.gni.frmk.tools.addon.operation.action.component.art.notifications.oldies.EnableAdapterNotification;
import com.gni.frmk.tools.addon.operation.action.component.art.notifications.oldies.ResumeAdapterNotification;
import com.gni.frmk.tools.addon.operation.action.component.art.notifications.oldies.SuspendAdapterNotification;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.handler.component.oldies.ChangeComponentStateHandler;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 16:27
 *
 * @author: e03229
 */
public class ChangeAdapterNotificationStateHandler
        extends ChangeComponentStateHandler<ChangeAdapterNotificationState, AdapterId, ActivableState, InvokeContext> {

    @Override
    protected List<Action<?>> defineActions(ActivableState oldState, ActivableState newState, AdapterId id) {
        EnableStatus oldEnabled = oldState.getEnabled();
        EnableStatus newEnabled = newState.getEnabled();
        List<Action<?>> actions = Lists.newArrayList();
        if (EnableStatus.isEnabling(oldEnabled, newEnabled)) {
            actions.add(new EnableAdapterNotification(id));
        }
        if (EnableStatus.isDisabling(oldEnabled, newEnabled)) {
            actions.add(new DisableAdapterNotification(id));
        }
        ActivableStatus oldActivated = oldState.getActivable();
        ActivableStatus newActivated = newState.getActivable();
        if (ActivableStatus.isActivation(oldActivated, newActivated)) {
            actions.add(new ResumeAdapterNotification(id));
        }
        if (ActivableStatus.isDesactivation(oldActivated, newActivated)) {
            actions.add(new SuspendAdapterNotification(id));
        }
        return actions;
    }

    @Override
    public Class<? extends ChangeAdapterNotificationState> getActionType() {
        return ChangeAdapterNotificationState.class;
    }
}
