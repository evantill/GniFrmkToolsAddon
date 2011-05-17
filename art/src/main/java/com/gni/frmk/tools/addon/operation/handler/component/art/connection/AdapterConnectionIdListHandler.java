package com.gni.frmk.tools.addon.operation.handler.component.art.connection;

import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.connection.AdapterConnectionIdList;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.operation.handler.component.art.AdapterTypeAwareHandler;
import com.gni.frmk.tools.addon.operation.handler.component.art.RetrieveAdapterTypesListHandler;
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
public class AdapterConnectionIdListHandler
        extends AdapterTypeAwareHandler<AdapterConnectionIdList, ListResult<AdapterId>, AdapterId>
        implements ActionHandler<AdapterConnectionIdList, ListResult<AdapterId>, InvokeContext> {

    private final RetrieveAdapterTypesListHandler getAllAdapterType = new RetrieveAdapterTypesListHandler();

    public AdapterConnectionIdListHandler() {
        super("pub.art.connection:listAdapterConnections");
    }

    @Override
    public Class<AdapterConnectionIdList> getActionType() {
        return AdapterConnectionIdList.class;
    }

    @Override
    protected AdapterConnectionIdList newFilteredAction(String adapterType) {
        return new AdapterConnectionIdList(adapterType);
    }

    @Override
    protected ListResult<AdapterId> newListResult(List<AdapterId> idList) {
        return new ListResult<AdapterId>(idList);
    }

    @Override
    protected ListResult<AdapterId> parseOutput(AdapterConnectionIdList action, IData output)
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
    protected IData prepareInput(AdapterConnectionIdList action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getAdapterTypeFilter()}
        });
    }
}
