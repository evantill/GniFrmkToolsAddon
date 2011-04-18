package com.gni.frmk.tools.addon.action.wm.art.notifications;

import com.gni.frmk.tools.addon.action.StringAction;
import com.gni.frmk.tools.addon.action.UpdatableCollectionAction;
import com.gni.frmk.tools.addon.model.component.ImmutableAdapterNotification.MutableAdapterNotification;
import com.gni.frmk.tools.addon.result.ListResult;
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
        extends StringAction<ListResult<MutableAdapterNotification>>
        implements UpdatableCollectionAction<MutableAdapterNotification, List<MutableAdapterNotification>, ListResult<MutableAdapterNotification>> {

    private final List<MutableAdapterNotification> notifications = Lists.newArrayList();

    public ListNotifications(String adapterType) {
        super(adapterType);
    }

    @Override
    public void setCollection(Collection<MutableAdapterNotification> collection) {
        notifications.addAll(collection);
    }

    @Override
    public List<MutableAdapterNotification> getCollection() {
        return Collections.unmodifiableList(notifications);
    }

    @Override
    public void addToCollection(MutableAdapterNotification element) {
        notifications.add(element);
    }

    @Override
    public boolean isUpdate() {
        return notifications.size() > 0;
    }

}
