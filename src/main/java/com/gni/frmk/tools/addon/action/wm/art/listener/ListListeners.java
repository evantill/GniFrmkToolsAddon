package com.gni.frmk.tools.addon.action.wm.art.listener;

import com.gni.frmk.tools.addon.model.component.id.AdapterId;
import com.gni.frmk.tools.addon.result.ListResult;
import com.gni.frmk.tools.addon.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:17
 *
 * @author: e03229
 */
public class ListListeners
        implements Action<ListResult<AdapterId>> {

    private final String adapterType;

    public ListListeners(String adapterType) {
        this.adapterType = adapterType;
    }

    public String getAdapterType() {
        return adapterType;
    }
}
