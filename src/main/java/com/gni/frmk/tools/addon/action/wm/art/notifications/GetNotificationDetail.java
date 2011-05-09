package com.gni.frmk.tools.addon.action.wm.art.notifications;

import com.gni.frmk.tools.addon.model.AdapterId;
import com.gni.frmk.tools.addon.model.component.AdapterNotification.Detail;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import ev.frmk.tools.plateform.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:03
 *
 * @author: e03229
 */
public class GetNotificationDetail
        implements Action<ComponentDetailResult<Detail>> {
    private final AdapterId id;

    public GetNotificationDetail(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }

}
