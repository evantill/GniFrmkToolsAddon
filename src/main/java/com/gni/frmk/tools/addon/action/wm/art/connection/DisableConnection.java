package com.gni.frmk.tools.addon.action.wm.art.connection;

import com.gni.frmk.tools.addon.model.AdapterId;
import com.gni.frmk.tools.addon.result.NoResult;
import ev.frmk.tools.plateform.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:15
 *
 * @author: e03229
 */
public class DisableConnection implements Action<NoResult> {

    private final AdapterId id;

    public DisableConnection(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }
}
