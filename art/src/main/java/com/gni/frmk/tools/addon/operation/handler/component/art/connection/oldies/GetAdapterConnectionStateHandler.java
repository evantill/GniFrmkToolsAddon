package com.gni.frmk.tools.addon.operation.handler.component.art.connection.oldies;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.connection.oldies.GetAdapterConnectionState;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.handler.component.oldies.GetComponentStateHandler;
import com.gni.frmk.tools.addon.operation.result.SingleResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:24
 *
 * @author: e03229
 */
public class GetAdapterConnectionStateHandler
        extends AbstractInvokeHandler<GetAdapterConnectionState, SingleResult<EnableState>>
        implements GetComponentStateHandler<GetAdapterConnectionState, AdapterId, EnableState, InvokeContext> {

    public GetAdapterConnectionStateHandler() {
        super("pub.art.connection:listAdapterConnections");
    }

    @Override
    public Class<GetAdapterConnectionState> getActionType() {
        return GetAdapterConnectionState.class;
    }

    @Override
    protected SingleResult<EnableState> parseOutput(GetAdapterConnectionState action, IData output)
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
                        EnableStatus enabled = EnableStatus.valueOf(IDataUtil.getString(curLoop, "connectionState")
                                                                             .toUpperCase());
                        return new SingleResult<EnableState>(new EnableState(enabled));
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new SingleResult<EnableState>(new EnableState(EnableStatus.UNKNOWN));
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(GetAdapterConnectionState action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getId().getAdapterType()}
        });
    }


}
