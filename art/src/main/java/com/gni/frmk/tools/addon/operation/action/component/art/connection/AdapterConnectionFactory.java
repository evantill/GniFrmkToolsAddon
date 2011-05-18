package com.gni.frmk.tools.addon.operation.action.component.art.connection;

import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection.AdapterConnectionDetail;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 15:06
 *
 * @author: e03229
 */
public class AdapterConnectionFactory
        implements ComponentFactory<AdapterId, EnableState, AdapterConnectionDetail, AdapterConnection> {
    @Override
    public AdapterConnection newComponent(AdapterId id, AdapterConnectionDetail detail, EnableState state) {
        AdapterConnection component = new AdapterConnection();
        component.setType(Type.ADAPTER_CONNECTION);
        component.setId(id);
        component.setCurrentState(state);
        component.setDetail(detail);
        return component;
    }

    @Override
    public ListComponentIds<AdapterId> newListComponentIdsAction() {
        return new ListAdapterConnectionIds();
    }

    @Override
    public GetAllComponents<AdapterConnection> newGetAllComponentAction() {
        return new GetAllAdapterConnections();
    }

    @Override
    public GetComponent<AdapterConnection, AdapterId> newGetComponentAction(AdapterId id) {
        return new GetAdapterConnection(id);
    }

    @Override
    public GetComponentDetail<AdapterConnectionDetail, AdapterId> newGetComponentDetailAction(AdapterId id) {
        return new GetAdapterConnectionDetail(id);
    }

    @Override
    public GetComponentState<EnableState, AdapterId> newGetComponentStateAction(AdapterId id) {
        return new GetAdapterConnectionState(id);
    }
}
