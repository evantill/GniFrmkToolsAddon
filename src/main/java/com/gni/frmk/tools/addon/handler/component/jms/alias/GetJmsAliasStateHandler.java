package com.gni.frmk.tools.addon.handler.component.jms.alias;

import com.gni.frmk.tools.addon.action.component.jms.alias.GetJmsAliasState;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState.ConnectableStatus;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.result.ComponentStateResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.api.action.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 16:34
 *
 * @author: e03229
 */
public class GetJmsAliasStateHandler
        extends AbstractInvokeHandler<GetJmsAliasState, ComponentStateResult<ConnectableState>>
        implements ActionHandler<GetJmsAliasState, ComponentStateResult<ConnectableState>, InvokeContext> {

    public GetJmsAliasStateHandler() {
        super("wm.server.jms:getConnectionAliasReport");
    }

    private ConnectableState parseConnectableState(IData doc) {
        IDataCursor cur = doc.getCursor();
        try {
            final EnableStatus enabled = EnableStatus.fromBoolean(IDataUtil.getBoolean(cur, "enabled", false));
            final ConnectableStatus connected = ConnectableStatus.fromBoolean(IDataUtil.getBoolean(cur, "connected", false));
            return new ConnectableState(enabled, connected);
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected ComponentStateResult<ConnectableState> parseOutput(GetJmsAliasState action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            IData[] dataList = IDataUtil.getIDataArray(cur, "aliasDataList");
            String aliasNameToFind = action.getId().getValue();
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String aliasName = IDataUtil.getString(curLoop, "aliasName");
                        if (!aliasNameToFind.equals(aliasName)) {
                            continue;
                        }
                        ConnectableState state = parseConnectableState(IDataUtil.getIData(curLoop, "trigger"));
                        return new ComponentStateResult<ConnectableState>(state);
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ComponentStateResult<ConnectableState>(new ConnectableState(EnableStatus.UNKNOWN, ConnectableStatus.UNKNOWN));
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(GetJmsAliasState in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<GetJmsAliasState> getActionType() {
        return GetJmsAliasState.class;
    }
}
