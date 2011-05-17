package com.gni.frmk.tools.addon.operation.action.component.art.notifications;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.ComponentAction;
import com.gni.frmk.tools.addon.operation.result.ComponentStateResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:03
 *
 * @author: e03229
 */
public class GetNotificationState
        implements ComponentAction<AdapterId,ComponentStateResult<ActivableState>> {

    private final AdapterId id;

    public GetNotificationState(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }

}
