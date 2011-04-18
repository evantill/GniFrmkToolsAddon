package com.gni.frmk.tools.addon.action.wm.art.notifications;

import com.gni.frmk.tools.addon.action.StringAction;
import com.gni.frmk.tools.addon.action.UpdatableCollectionAction;
import com.gni.frmk.tools.addon.result.ListResult;
import com.gni.frmk.tools.addon.model.component.AdapterNotification;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:18
 *
 * @author: e03229
 */
public class ListNotifications
        extends StringAction<ListResult<AdapterNotification>>
        implements UpdatableCollectionAction<AdapterNotification, List<AdapterNotification>, ListResult<AdapterNotification>> {

    private final List<AdapterNotification> notifications = Lists.newArrayList();

    public ListNotifications(String adapterType) {
        super(adapterType);
    }

    @Override
    public void setCollection(Collection<AdapterNotification> collection) {
        notifications.addAll(collection);
    }

    @Override
    public List<AdapterNotification> getCollection() {
        return Collections.unmodifiableList(notifications);
    }

    @Override
    public boolean isUpdate() {
        return notifications.size() > 0;
    }

}
