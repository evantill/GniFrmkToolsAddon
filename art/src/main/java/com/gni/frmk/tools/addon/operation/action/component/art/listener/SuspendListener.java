package com.gni.frmk.tools.addon.operation.action.component.art.listener;

import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.ComponentAction;
import com.gni.frmk.tools.addon.operation.result.NoResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:16
 *
 * @author: e03229
 */
public class SuspendListener implements ComponentAction<AdapterId,NoResult> {
    private final AdapterId id;

    public SuspendListener(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }
}
