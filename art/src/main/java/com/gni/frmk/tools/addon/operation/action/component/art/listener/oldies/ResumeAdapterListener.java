package com.gni.frmk.tools.addon.operation.action.component.art.listener.oldies;

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
public class ResumeAdapterListener implements ComponentAction<AdapterId,NoResult> {
    private final AdapterId id;

    public ResumeAdapterListener(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }
}