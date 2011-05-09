package com.gni.frmk.tools.addon.handler.wm.art.connection;

import com.gni.frmk.tools.addon.action.wm.art.connection.GetConnectionDetail;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.AdapterConnection.Detail;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.wm.data.*;
import ev.frmk.tools.plateform.api.action.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:56
 *
 * @author: e03229
 */
public class GetConnectionDetailHandler extends AbstractInvokeHandler<GetConnectionDetail, ComponentDetailResult<Detail>>
        implements ActionHandler<GetConnectionDetail, ComponentDetailResult<Detail>, InvokeContext> {

    public GetConnectionDetailHandler() {
        super("pub.art.connection:listAdapterConnections");
    }

    @Override
    public Class<GetConnectionDetail> getActionType() {
        return GetConnectionDetail.class;
    }

    @Override
    protected ComponentDetailResult<Detail> parseOutput(GetConnectionDetail action, IData output)
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
                        return new ComponentDetailResult<Detail>(new Detail(packageName));
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ComponentDetailResult<Detail>(new Detail());
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(GetConnectionDetail action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getId().getAdapterType()}
        });
    }


}

