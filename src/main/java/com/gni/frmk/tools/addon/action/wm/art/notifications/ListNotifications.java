package com.gni.frmk.tools.addon.action.wm.art.notifications;

import com.gni.frmk.tools.addon.model.component.id.AdapterId;
import com.gni.frmk.tools.addon.result.ListResult;
import com.gni.frmk.tools.addon.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:18
 *
 * @author: e03229
 */
public class ListNotifications
        implements Action<ListResult<AdapterId>> {

    private final String adapterType;

    public ListNotifications(String adapterType) {
        this.adapterType = adapterType;
    }

    public String getAdapterType() {
        return adapterType;
    }
}
