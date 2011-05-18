package com.gni.frmk.tools.addon.operation.handler.component.art.connection;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.connection.ListAdapterConnectionIds;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.handler.component.art.ListAdapterTypeAwareIdsHandler;
import com.gni.frmk.tools.addon.operation.result.ListResult;
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
public class ListAdapterConnectionIdsHandler
        extends ListAdapterTypeAwareIdsHandler<ListAdapterConnectionIds, ListResult<AdapterId>, AdapterId>
        implements ActionHandler<ListAdapterConnectionIds, ListResult<AdapterId>, InvokeContext> {

    public ListAdapterConnectionIdsHandler() {
        super("pub.art.connection:listAdapterConnections");
    }

    @Override
    public Class<ListAdapterConnectionIds> getActionType() {
        return ListAdapterConnectionIds.class;
    }

    @Override
    protected ListAdapterConnectionIds newFilteredAction(String adapterType) {
        return new ListAdapterConnectionIds(adapterType);
    }

    @Override
    protected ListResult<AdapterId> newListResult(List<AdapterId> idList) {
        return new ListResult<AdapterId>(idList);
    }

    @Override
    protected ListResult<AdapterId> parseOutput(ListAdapterConnectionIds action, IData output)
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
                        result.add(new AdapterId(connectionAlias, action.getAdapterTypeFilter()));
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
    protected IData prepareInput(ListAdapterConnectionIds action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getAdapterTypeFilter()}
        });
    }
}
