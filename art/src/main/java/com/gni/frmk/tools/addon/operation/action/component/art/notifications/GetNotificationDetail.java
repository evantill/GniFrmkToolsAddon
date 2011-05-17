package com.gni.frmk.tools.addon.operation.action.component.art.notifications;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotification.AdapterNotificationDetail;
import com.gni.frmk.tools.addon.operation.result.ComponentDetailResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:03
 *
 * @author: e03229
 */
public class GetNotificationDetail
        implements Action<ComponentDetailResult<AdapterNotificationDetail>> {
    private final AdapterId id;

    public GetNotificationDetail(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }

}
