package com.gni.frmk.tools.addon.operation.action.component.art.listener;

import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.ComponentAction;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener.AdapterListenerDetail;
import com.gni.frmk.tools.addon.operation.result.ComponentDetailResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 18:38
 *
 * @author: e03229
 */
public class GetListenerDetail
        implements ComponentAction<AdapterId,ComponentDetailResult<AdapterListenerDetail>> {
    private final AdapterId id;

    public GetListenerDetail(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }

}
