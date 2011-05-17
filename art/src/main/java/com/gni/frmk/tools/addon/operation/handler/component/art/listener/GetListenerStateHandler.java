package com.gni.frmk.tools.addon.operation.handler.component.art.listener;

import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.operation.action.component.art.listener.GetListenerState;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.model.component.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.operation.result.ComponentStateResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;

import static com.gni.frmk.tools.addon.operation.handler.component.art.ListenerNotificationUtils.defineState;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 18:41
 *
 * @author: e03229
 */
public class GetListenerStateHandler
        extends AbstractInvokeHandler<GetListenerState, ComponentStateResult<ActivableState>>
        implements ActionHandler<GetListenerState, ComponentStateResult<ActivableState>, InvokeContext> {


    public GetListenerStateHandler() {
        super("pub.art.listener:listAdapterListeners");
    }

    @Override
    public Class<GetListenerState> getActionType() {
        return GetListenerState.class;
    }

    @Override
    protected ComponentStateResult<ActivableState> parseOutput(GetListenerState action, IData output) {
        //TODO TESTER chez ALU sur les SAP Listeners
        IDataCursor cur = output.getCursor();
        try {
            IData[] dataList = IDataUtil.getIDataArray(cur, "listenerDataList");
            final String componentIdToFind = action.getId().getName();
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String listenerNodeName = IDataUtil.getString(curLoop, "notificationNodeName");
                        if (!componentIdToFind.equals(listenerNodeName)) {
                            continue;
                        }
                        ActivableState state = defineState(IDataUtil.getString(curLoop, "notificationEnabled"));
                        return new ComponentStateResult<ActivableState>(state);
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ComponentStateResult<ActivableState>(new ActivableState(EnableStatus.UNKNOWN, ActivableStatus.UNKNOWN));
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(GetListenerState action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getId().getAdapterType()}
        });
    }
}
