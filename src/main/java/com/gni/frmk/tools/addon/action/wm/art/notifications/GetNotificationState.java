package com.gni.frmk.tools.addon.action.wm.art.notifications;

import com.gni.frmk.tools.addon.model.ActivableState;
import com.gni.frmk.tools.addon.model.AdapterId;
import com.gni.frmk.tools.addon.result.ComponentStateResult;
import ev.frmk.tools.plateform.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:03
 *
 * @author: e03229
 */
public class GetNotificationState
        implements Action<ComponentStateResult<ActivableState>> {

    private final AdapterId id;

    public GetNotificationState(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }

}
