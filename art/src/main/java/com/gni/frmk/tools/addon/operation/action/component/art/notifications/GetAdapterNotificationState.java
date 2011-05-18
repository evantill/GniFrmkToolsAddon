package com.gni.frmk.tools.addon.operation.action.component.art.notifications;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:03
 *
 * @author: e03229
 */
public class GetAdapterNotificationState
        extends GetComponentState<ActivableState, AdapterId> {
    public GetAdapterNotificationState(AdapterId id) {
        super(id);
    }
}
