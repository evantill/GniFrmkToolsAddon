package com.gni.frmk.tools.addon.action.component.art.connection;

import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.result.NoResult;
import com.gni.frmk.tools.addon.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:15
 *
 * @author: e03229
 */
public class EnableConnection implements Action<NoResult> {

    private final AdapterId id;

    public EnableConnection(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }
}
