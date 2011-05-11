package com.gni.frmk.tools.addon.action.wm.art.connection;

import com.gni.frmk.tools.addon.model.component.id.AdapterId;
import com.gni.frmk.tools.addon.result.ListResult;
import com.gni.frmk.tools.addon.api.action.Action;

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
