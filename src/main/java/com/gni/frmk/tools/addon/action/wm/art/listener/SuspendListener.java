package com.gni.frmk.tools.addon.action.wm.art.listener;

import com.gni.frmk.tools.addon.action.StringAction;
import com.gni.frmk.tools.addon.model.AdapterId;
import com.gni.frmk.tools.addon.result.NoResult;
import ev.frmk.tools.plateform.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:16
 *
 * @author: e03229
 */
public class SuspendListener implements Action<NoResult> {
    private final AdapterId id;

    public SuspendListener(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }
}
