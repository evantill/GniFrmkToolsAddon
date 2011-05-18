package com.gni.frmk.tools.addon.operation.action.component.art.notifications;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotification;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotification.AdapterNotificationDetail;
import com.gni.frmk.tools.addon.operation.action.component.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 15:38
 *
 * @author: e03229
 */
public class AdapterNotificationFactory
        implements ComponentFactory<AdapterId, ActivableState, AdapterNotificationDetail, AdapterNotification> {
    @Override
    public AdapterNotification newComponent(AdapterId id, AdapterNotificationDetail detail, ActivableState state) {
        AdapterNotification component = new AdapterNotification();
        component.setType(Type.ADAPTER_NOTIFICATION);
        component.setId(id);
        component.setCurrentState(state);
        component.setDetail(detail);
        return component;
    }

    @Override
    public ListComponentIds<AdapterId> newListComponentIdsAction() {
        return new ListAdapterNotificationIds();
    }

    @Override
    public GetAllComponents<AdapterNotification> newGetAllComponentAction() {
        return new GetAllAdapterNotifications();
    }

    @Override
    public GetComponent<AdapterNotification, AdapterId> newGetComponentAction(AdapterId id) {
        return new GetAdapterNotification(id);
    }

    @Override
    public GetComponentDetail<AdapterNotificationDetail, AdapterId> newGetComponentDetailAction(AdapterId id) {
        return new GetAdapterNotificationDetail(id);
    }

    @Override
    public GetComponentState<ActivableState, AdapterId> newGetComponentStateAction(AdapterId id) {
        return new GetAdapterNotificationState(id);
    }
}
