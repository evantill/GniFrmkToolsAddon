package com.gni.frmk.tools.addon.operation.action.component.art.notifications.oldies;

import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.oldies.ListAdapterTypeAwareComponentIds;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:18
 *
 * @author: e03229
 */
public class ListAdapterNotificationIds extends ListAdapterTypeAwareComponentIds<AdapterId> {
    public ListAdapterNotificationIds(String adapterTypeFilter) {
        super(adapterTypeFilter);
    }

    public ListAdapterNotificationIds() {
    }
}