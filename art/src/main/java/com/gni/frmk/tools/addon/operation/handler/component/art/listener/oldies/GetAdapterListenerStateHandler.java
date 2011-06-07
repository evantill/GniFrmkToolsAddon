package com.gni.frmk.tools.addon.operation.handler.component.art.listener.oldies;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.listener.oldies.GetAdapterListenerState;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.handler.component.oldies.GetComponentStateHandler;
import com.gni.frmk.tools.addon.operation.result.SingleResult;
import com.wm.data.*;

import static com.gni.frmk.tools.addon.operation.handler.component.art.ListenerNotificationUtils.defineState;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 18:41
 *
 * @author: e03229
 */
public class GetAdapterListenerStateHandler
        extends AbstractInvokeHandler<GetAdapterListenerState, SingleResult<ActivableState>>
        implements GetComponentStateHandler<GetAdapterListenerState, AdapterId, ActivableState, InvokeContext> {


    public GetAdapterListenerStateHandler() {
        super("pub.art.listener:listAdapterListeners");
    }

    @Override
    public Class<GetAdapterListenerState> getActionType() {
        return GetAdapterListenerState.class;
    }

    @Override
    protected SingleResult<ActivableState> parseOutput(GetAdapterListenerState action, IData output) {
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
                        return new SingleResult<ActivableState>(state);
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new SingleResult<ActivableState>(new ActivableState(EnableStatus.UNKNOWN, ActivableStatus.UNKNOWN));
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(GetAdapterListenerState action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getId().getAdapterType()}
        });
    }
}
