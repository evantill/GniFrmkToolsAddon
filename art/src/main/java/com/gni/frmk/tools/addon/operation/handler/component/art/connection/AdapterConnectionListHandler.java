package com.gni.frmk.tools.addon.operation.handler.component.art.connection;

import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.connection.AdapterConnectionIdList;
import com.gni.frmk.tools.addon.operation.action.component.art.connection.AdapterConnectionList;
import com.gni.frmk.tools.addon.operation.action.component.art.connection.GetConnectionDetail;
import com.gni.frmk.tools.addon.operation.action.component.art.connection.GetConnectionState;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.AbstractComponentListHandler;
import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection.AdapterConnectionDetail;
import com.gni.frmk.tools.addon.operation.result.ComponentDetailResult;
import com.gni.frmk.tools.addon.operation.result.ComponentStateResult;
import com.gni.frmk.tools.addon.operation.result.ListResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 13:56
 *
 * @author: e03229
 */
public class AdapterConnectionListHandler
        extends AbstractComponentListHandler<AdapterId, AdapterConnectionDetail, EnableState, AdapterConnection, AdapterConnectionList, InvokeContext>
        implements ActionHandler<AdapterConnectionList, ListResult<AdapterConnection>, InvokeContext> {

    @Override
    public Class<AdapterConnectionList> getActionType() {
        return AdapterConnectionList.class;
    }

    @Override
    protected Action<ListResult<AdapterId>> newListIdAction() {
        return new AdapterConnectionIdList();
    }

    @Override
    protected Action<ComponentDetailResult<AdapterConnectionDetail>> newGetComponentDetailAction(AdapterId id) {
        return new GetConnectionDetail(id);
    }

    @Override
    protected Action<ComponentStateResult<EnableState>> newGetComponentStateAction(AdapterId id) {
        return new GetConnectionState(id);
    }

    @Override
    protected AdapterConnection newComponent(AdapterId id, AdapterConnectionDetail detail, EnableState state) throws DispatchException {
        AdapterConnection component = new AdapterConnection();
        component.setType(Type.ADAPTER_CONNECTION);
        component.setId(id);
        component.setDetail(detail);
        component.setCurrentState(state);
        return component;
    }


}
