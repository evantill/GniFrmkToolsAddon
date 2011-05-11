package com.gni.frmk.tools.addon.handler.component.art.connection;

import com.gni.frmk.tools.addon.action.component.art.connection.GetConnectionState;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.result.ComponentStateResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:24
 *
 * @author: e03229
 */
public class GetConnectionStateHandler
        extends AbstractInvokeHandler<GetConnectionState, ComponentStateResult<EnableState>>
        implements ActionHandler<GetConnectionState, ComponentStateResult<EnableState>, InvokeContext> {

    public GetConnectionStateHandler() {
        super("pub.art.connection:listAdapterConnections");
    }

    @Override
    public Class<GetConnectionState> getActionType() {
        return GetConnectionState.class;
    }

    @Override
    protected ComponentStateResult<EnableState> parseOutput(GetConnectionState action, IData output)
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
                        return new ComponentStateResult<EnableState>(new EnableState(enabled));
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ComponentStateResult<EnableState>(new EnableState(EnableStatus.UNKNOWN));
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(GetConnectionState action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getId().getAdapterType()}
        });
    }


}
