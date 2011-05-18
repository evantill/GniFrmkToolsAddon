package com.gni.frmk.tools.addon.operation.action.component.art.listener;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener.AdapterListenerDetail;
import com.gni.frmk.tools.addon.operation.action.component.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 15:38
 *
 * @author: e03229
 */
public class AdapterListenerFactory
        implements ComponentFactory<AdapterId, ActivableState, AdapterListenerDetail, AdapterListener> {

    @Override
    public AdapterListener newComponent(AdapterId id, AdapterListenerDetail detail, ActivableState state) {
        AdapterListener component = new AdapterListener();
        component.setType(Type.ADAPTER_LISTENER);
        component.setId(id);
        component.setCurrentState(state);
        component.setDetail(detail);
        return component;
    }

    @Override
    public ListComponentIds<AdapterId> newListComponentIdsAction() {
        return new ListAdapterListenerIds();
    }

    @Override
    public GetAllComponents<AdapterListener> newGetAllComponentAction() {
        return new GetAllAdapterListeners();
    }

    @Override
    public GetComponent<AdapterListener, AdapterId> newGetComponentAction(AdapterId id) {
        return new GetAdapterListener(id);
    }

    @Override
    public GetComponentDetail<AdapterListenerDetail, AdapterId> newGetComponentDetailAction(AdapterId id) {
        return new GetAdapterListenerDetail(id);
    }

    @Override
    public GetComponentState<ActivableState, AdapterId> newGetComponentStateAction(AdapterId id) {
        return new GetAdapterListenerState(id);
    }
}
