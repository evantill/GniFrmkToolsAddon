package com.gni.frmk.tools.addon.operation.action.component.art.notifications;

import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.AbstractAdapterTypeAwareAction;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:18
 *
 * @author: e03229
 */
public class NotificationIdList extends AbstractAdapterTypeAwareAction<AdapterId> {
    public NotificationIdList(String adapterTypeFilter) {
        super(adapterTypeFilter);
    }

    public NotificationIdList() {
    }
}