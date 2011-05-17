package com.gni.frmk.tools.addon.operation.action.component.art.listener;

import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.ComponentAction;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.operation.result.ComponentStateResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 18:38
 *
 * @author: e03229
 */
public class GetListenerState
        implements ComponentAction<AdapterId,ComponentStateResult<ActivableState>> {

    private final AdapterId id;

    public GetListenerState(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }

}
