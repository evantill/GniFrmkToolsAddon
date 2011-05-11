package com.gni.frmk.tools.addon.action.wm.art.notifications;

import com.gni.frmk.tools.addon.model.component.id.AdapterId;
import com.gni.frmk.tools.addon.result.NoResult;
import com.gni.frmk.tools.addon.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:17
 *
 * @author: e03229
 */
public class ResumeNotification implements Action<NoResult> {
    private final AdapterId id;

    public ResumeNotification(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }
}