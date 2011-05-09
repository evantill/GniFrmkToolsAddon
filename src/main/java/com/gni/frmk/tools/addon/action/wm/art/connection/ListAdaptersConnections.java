package com.gni.frmk.tools.addon.action.wm.art.connection;

import com.gni.frmk.tools.addon.model.AdapterId;
import com.gni.frmk.tools.addon.result.ListResult;
import ev.frmk.tools.plateform.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:16
 *
 * @author: e03229
 */
public class ListAdaptersConnections implements Action<ListResult<AdapterId>> {

    private final String adapterType;

    public ListAdaptersConnections(String adapterType) {
        this.adapterType = adapterType;
    }

    public String getAdapterType() {
        return adapterType;
    }
}
