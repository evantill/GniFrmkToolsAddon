package com.gni.frmk.tools.addon.action.wm.art.listener;

import com.gni.frmk.tools.addon.model.component.id.AdapterId;
import com.gni.frmk.tools.addon.model.component.AdapterListener.Detail;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.gni.frmk.tools.addon.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 18:38
 *
 * @author: e03229
 */
public class GetListenerDetail
        implements Action<ComponentDetailResult<Detail>> {
    private final AdapterId id;

    public GetListenerDetail(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }

}
