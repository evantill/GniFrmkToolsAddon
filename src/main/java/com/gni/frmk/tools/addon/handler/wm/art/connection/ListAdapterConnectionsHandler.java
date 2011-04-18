package com.gni.frmk.tools.addon.handler.wm.art.connection;

import com.gni.frmk.tools.addon.action.wm.art.connection.ListAdaptersConnections;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.art.AdapterTypeAwareHandler;
import com.gni.frmk.tools.addon.model.component.ImmutableAdapterConnection.MutableAdapterConnection;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.result.ListResult;
import com.google.common.collect.Maps;
import com.wm.data.*;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:22
 *
 * @author: e03229
 */
public class ListAdapterConnectionsHandler extends AdapterTypeAwareHandler<ListAdaptersConnections, ListResult<MutableAdapterConnection>>
        implements ActionHandler<ListAdaptersConnections, ListResult<MutableAdapterConnection>, InvokeContext> {

    public ListAdapterConnectionsHandler() {
        super("pub.art.connection:listAdapterConnections");
    }

    @Override
    public Class<ListAdaptersConnections> getActionType() {
        return ListAdaptersConnections.class;
    }

    @Override
    protected ListResult<MutableAdapterConnection> parseOutput(ListAdaptersConnections action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            Map<String, MutableAdapterConnection> values = Maps.newHashMap();
            for (MutableAdapterConnection connection : action.getCollection()) {
                values.put(connection.getComponentId().asString(), connection);
            }
            IData[] dataList = IDataUtil.getIDataArray(cur, "connectionDataList");
            final String adapterType = action.getParameter();
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String connectionAlias = IDataUtil.getString(curLoop, "connectionAlias");
                        MutableAdapterConnection value = values.get(connectionAlias);
                        if (value == null && action.isUpdate()) {
                            continue;
                        } else if (value == null && !action.isUpdate()) {
                            value = new MutableAdapterConnection();
                            value.setAlias(IDataUtil.getString(curLoop, "connectionAlias"));
                            value.setAdapterType(adapterType);
                            value.setPackageName(IDataUtil.getString(curLoop, "packageName"));
                        }
                        EnableStatus enabled = EnableStatus.valueOf(IDataUtil.getString(curLoop, "connectionState")
                                                                             .toUpperCase());
                        value.setState(new EnableState(enabled));
                        values.put(value.getComponentId().asString(), value);
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<MutableAdapterConnection>(values.values());
        } finally {
            cur.destroy();
        }

    }
}
