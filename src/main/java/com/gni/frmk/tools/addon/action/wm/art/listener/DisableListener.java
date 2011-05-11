package com.gni.frmk.tools.addon.action.wm.art.listener;

import com.gni.frmk.tools.addon.model.component.id.AdapterId;
import com.gni.frmk.tools.addon.result.NoResult;
import com.gni.frmk.tools.addon.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:16
 *
 * @author: e03229
 */
public class DisableListener implements Action<NoResult> {
    private final AdapterId id;

    public DisableListener(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }
}
