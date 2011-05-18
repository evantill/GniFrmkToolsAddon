package com.gni.frmk.tools.addon.operation.handler.component.art.connection;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection.AdapterConnectionDetail;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.connection.GetAdapterConnectionDetail;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentDetailHandler;
import com.gni.frmk.tools.addon.operation.result.SingleResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:56
 *
 * @author: e03229
 */
public class GetAdapterConnectionDetailHandler
        extends AbstractInvokeHandler<GetAdapterConnectionDetail, SingleResult<AdapterConnectionDetail>>
        implements GetComponentDetailHandler<GetAdapterConnectionDetail, AdapterId, AdapterConnectionDetail, InvokeContext> {

    public GetAdapterConnectionDetailHandler() {
        super("pub.art.connection:listAdapterConnections");
    }

    @Override
    public Class<GetAdapterConnectionDetail> getActionType() {
        return GetAdapterConnectionDetail.class;
    }

    @Override
    protected SingleResult<AdapterConnectionDetail> parseOutput(GetAdapterConnectionDetail action, IData output)
            throws ParseOutputException {
        IDataCursor cur = output.getCursor();
        final String componentIdToFind = action.getId().getName();
        try {
            IData[] dataList = IDataUtil.getIDataArray(cur, "connectionDataList");
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String connectionAlias = IDataUtil.getString(curLoop, "connectionAlias");
                        if (!componentIdToFind.equals(connectionAlias)) {
                            continue;
                        }
                        String packageName = IDataUtil.getString(curLoop, "packageName");
                        return new SingleResult<AdapterConnectionDetail>(new AdapterConnectionDetail(packageName));
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new SingleResult<AdapterConnectionDetail>(new AdapterConnectionDetail());
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(GetAdapterConnectionDetail action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getId().getAdapterType()}
        });
    }


}

