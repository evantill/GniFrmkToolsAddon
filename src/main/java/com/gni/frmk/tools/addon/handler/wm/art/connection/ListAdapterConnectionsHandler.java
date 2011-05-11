package com.gni.frmk.tools.addon.handler.wm.art.connection;

import com.gni.frmk.tools.addon.action.wm.art.connection.ListAdaptersConnections;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.id.AdapterId;
import com.gni.frmk.tools.addon.result.ListResult;
import com.google.common.collect.Lists;
import com.wm.data.*;
import com.gni.frmk.tools.addon.api.action.ActionHandler;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:22
 *
 * @author: e03229
 */
public class ListAdapterConnectionsHandler
        extends AbstractInvokeHandler<ListAdaptersConnections, ListResult<AdapterId>>
        implements ActionHandler<ListAdaptersConnections, ListResult<AdapterId>, InvokeContext> {


    public ListAdapterConnectionsHandler() {
        super("pub.art.connection:listAdapterConnections");
    }

    @Override
    public Class<ListAdaptersConnections> getActionType() {
        return ListAdaptersConnections.class;
    }

    @Override
    protected ListResult<AdapterId> parseOutput(ListAdaptersConnections action, IData output)
            throws ParseOutputException {
        IDataCursor cur = output.getCursor();
        try {
            IData[] dataList = IDataUtil.getIDataArray(cur, "connectionDataList");
            List<AdapterId> result = Lists.newArrayList();
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String connectionAlias = IDataUtil.getString(curLoop, "connectionAlias");
                        result.add(new AdapterId(connectionAlias, action.getAdapterType()));
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<AdapterId>(result);
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(ListAdaptersConnections action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getAdapterType()}
        });
    }
}
