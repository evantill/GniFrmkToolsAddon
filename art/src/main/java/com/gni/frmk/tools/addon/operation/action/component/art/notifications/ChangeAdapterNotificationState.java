package com.gni.frmk.tools.addon.operation.action.component.art.notifications;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotification;
import com.gni.frmk.tools.addon.operation.action.component.ChangeComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 15:39
 *
 * @author: e03229
 */
public class ChangeAdapterNotificationState
        extends ChangeComponentState<AdapterId, ActivableState> {
    public ChangeAdapterNotificationState(ActivableState newComponentState, AdapterNotification component) {
        super(newComponentState, component);
    }
}
