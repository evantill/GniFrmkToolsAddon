package com.gni.frmk.tools.addon.command.handler.wm.art;

import com.gni.frmk.tools.addon.command.action.wm.art.ListAdaptersConnections;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.result.ListResult;
import com.gni.frmk.tools.addon.model.component.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.google.common.collect.Lists;
import com.wm.data.*;

import java.util.List;

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
            List<AdapterConnection> values = Lists.newArrayList();
            IData[] dataList = IDataUtil.getIDataArray(cur, "connectionDataList");
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        EnableStatus enabled = EnableStatus.valueOf(IDataUtil.getString(curLoop, "connectionState")
                                                                             .toUpperCase());
                        values.add(AdapterConnection.builder()
                                                    .alias(IDataUtil.getString(curLoop, "connectionAlias"))
                                                    .adapterType(IDataUtil.getString(curLoop, "name"))
                                                    .packageName(IDataUtil.getString(curLoop, "packageName"))
                                                    .defineState(new EnableState(enabled)).build());
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<AdapterConnection>(values);
        } finally {
            cur.destroy();
        }
    }
}
