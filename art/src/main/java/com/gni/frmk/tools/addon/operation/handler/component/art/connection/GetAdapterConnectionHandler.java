package com.gni.frmk.tools.addon.operation.handler.component.art.connection;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection.AdapterConnectionDetail;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.connection.AdapterConnectionFactory;
import com.gni.frmk.tools.addon.operation.action.component.art.connection.GetAdapterConnection;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 15:14
 *
 * @author: e03229
 */
public class GetAdapterConnectionHandler
        extends GetComponentHandler<AdapterId, AdapterConnectionDetail, EnableState, AdapterConnection, GetAdapterConnection, InvokeContext> {
    public GetAdapterConnectionHandler() {
        super(new AdapterConnectionFactory());
    }

    @Override
    public Class<? extends GetAdapterConnection> getActionType() {
        return GetAdapterConnection.class;
    }
}
