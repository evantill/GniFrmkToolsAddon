package com.gni.frmk.tools.addon.handler.wm.art.connection;

import com.gni.frmk.tools.addon.action.wm.art.connection.ListAdaptersConnections;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.art.AdapterTypeAwareHandler;
import com.gni.frmk.tools.addon.result.ListResult;
import com.gni.frmk.tools.addon.model.component.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.AdapterConnection.AdapterConnectionBuilder;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
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
public class ListAdapterConnectionsHandler extends AdapterTypeAwareHandler<ListAdaptersConnections, ListResult<AdapterConnection>>
        implements ActionHandler<ListAdaptersConnections, ListResult<AdapterConnection>, InvokeContext> {

    public ListAdapterConnectionsHandler() {
        super("pub.art.connection:listAdapterConnections");
    }

    @Override
    public Class<ListAdaptersConnections> getActionType() {
        return ListAdaptersConnections.class;
    }

    @Override
    protected ListResult<AdapterConnection> parseOutput(ListAdaptersConnections action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            Map<String, AdapterConnection> values = Maps.newHashMap();
            for (AdapterConnection connection : action.getCollection()) {
                values.put(connection.getComponentId().asString(), connection);
            }
            IData[] dataList = IDataUtil.getIDataArray(cur, "connectionDataList");
            final String adapterType=action.getParameter();
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String connectionAlias = IDataUtil.getString(curLoop, "connectionAlias");
                        AdapterConnection value = values.get(connectionAlias);
                        if (value == null && action.isUpdate()) {
                            continue;
                        }
                        AdapterConnectionBuilder builder = AdapterConnection.builder();
                        if (action.isUpdate()) {
                            builder.from(value);
                        } else {
                            builder.alias(IDataUtil.getString(curLoop, "connectionAlias"))
                                   .adapterType(adapterType)
                                   .packageName(IDataUtil.getString(curLoop, "packageName"));
                        }
                        EnableStatus enabled = EnableStatus.valueOf(IDataUtil.getString(curLoop, "connectionState")
                                                                             .toUpperCase());
                        builder.defineState(new EnableState(enabled));
                        //add result
                        value = builder.build();
                        values.put(value.getComponentId().asString(),value);
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<AdapterConnection>(values.values());
        } finally {
            cur.destroy();
        }

    }
}
