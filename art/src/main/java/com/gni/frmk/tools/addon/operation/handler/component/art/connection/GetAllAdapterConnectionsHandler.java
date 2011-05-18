package com.gni.frmk.tools.addon.operation.handler.component.art.connection;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection.AdapterConnectionDetail;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.connection.AdapterConnectionFactory;
import com.gni.frmk.tools.addon.operation.action.component.art.connection.GetAllAdapterConnections;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.handler.component.GetAllComponentsHandler;
import com.gni.frmk.tools.addon.operation.result.ListResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 13:56
 *
 * @author: e03229
 */
public class GetAllAdapterConnectionsHandler
        extends GetAllComponentsHandler<AdapterId, AdapterConnectionDetail, EnableState, AdapterConnection, GetAllAdapterConnections, InvokeContext>
        implements ActionHandler<GetAllAdapterConnections, ListResult<AdapterConnection>, InvokeContext> {

    public GetAllAdapterConnectionsHandler() {
        super(new AdapterConnectionFactory());
    }

    @Override
    public Class<GetAllAdapterConnections> getActionType() {
        return GetAllAdapterConnections.class;
    }

}
