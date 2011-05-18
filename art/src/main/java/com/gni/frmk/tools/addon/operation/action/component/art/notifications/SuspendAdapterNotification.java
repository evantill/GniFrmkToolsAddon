package com.gni.frmk.tools.addon.operation.action.component.art.notifications;

import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.result.NoResult;
import com.gni.frmk.tools.addon.operation.api.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:17
 *
 * @author: e03229
 */
public class SuspendAdapterNotification implements Action<NoResult> {
    private final AdapterId id;

    public SuspendAdapterNotification(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }
}