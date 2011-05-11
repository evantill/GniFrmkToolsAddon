package com.gni.frmk.tools.addon.handler.wm.root.port;

import com.gni.frmk.tools.addon.action.wm.root.port.GetPortState;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.result.ComponentStateResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.api.action.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 13:32
 *
 * @author: e03229
 */
public class GetPortStateHandler
        extends AbstractInvokeHandler<GetPortState, ComponentStateResult<ActivableState>>
        implements ActionHandler<GetPortState, ComponentStateResult<ActivableState>, InvokeContext> {

    public GetPortStateHandler() {
        super("wm.server.ports:listListeners");
    }

    @Override
    protected IData prepareInput(GetPortState action) throws ParseInputException {
        return EMPTY_INPUT;
    }

    @Override
    protected ComponentStateResult<ActivableState> parseOutput(GetPortState action, IData output) throws ParseOutputException {
        IDataCursor cur = output.getCursor();
        try {
            IData[] tasksDatas = IDataUtil.getIDataArray(cur, "listeners");
            if (tasksDatas != null) {
                final String idToFind = action.getId().getId();
                final String pkgToFind = action.getId().getPackageName();
                for (IData portData : tasksDatas) {
                    IDataCursor portCur = portData.getCursor();
                    try {
                        String key = IDataUtil.getString(portCur, "key");
                        String pkg = IDataUtil.getString(portCur, "pkg");
                        if (!pkgToFind.equals(pkg) || !idToFind.equals(key)) {
                            continue;
                        }
                        ActivableState activableState = defineState(portCur);
                        return new ComponentStateResult<ActivableState>(activableState);
                    } finally {
                        portCur.destroy();
                    }
                }
            }
            return new ComponentStateResult<ActivableState>(new ActivableState(EnableStatus.UNKNOWN, ActivableStatus.UNKNOWN));
        } finally {
            cur.destroy();
        }
    }

    private ActivableState defineState(IDataCursor curDoc) {
        EnableStatus enabled = EnableStatus.fromBooleanString(IDataUtil.getString(curDoc, "enabled"));
        ActivableStatus activable =ActivableStatus.invert(ActivableStatus.fromBooleanString(IDataUtil.getString(curDoc, "suspended")));
        return new ActivableState(enabled, activable);
    }

    @Override
    public Class<GetPortState> getActionType() {
        return GetPortState.class;
    }

}