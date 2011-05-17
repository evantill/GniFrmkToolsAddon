package com.gni.frmk.tools.addon.operation.handler.component.art.notification;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.action.component.art.notifications.GetNotificationState;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.operation.result.ComponentStateResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;

import static com.gni.frmk.tools.addon.operation.handler.component.art.ListenerNotificationUtils.defineState;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:06
 *
 * @author: e03229
 */
public class GetNotificationStateHandler extends AbstractInvokeHandler<GetNotificationState, ComponentStateResult<ActivableState>>
        implements ActionHandler<GetNotificationState, ComponentStateResult<ActivableState>, InvokeContext> {


    public GetNotificationStateHandler() {
        super("pub.art.notification:listAdapterPollingNotifications");
    }

    @Override
    public Class<GetNotificationState> getActionType() {
        return GetNotificationState.class;
    }

    @Override
    protected ComponentStateResult<ActivableState> parseOutput(GetNotificationState action, IData output) {
        //TODO TESTER chez ALU sur les SAP Listeners
        IDataCursor cur = output.getCursor();
        try {
            IData[] dataList = IDataUtil.getIDataArray(cur, "notificationDataList");
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
    protected IData prepareInput(GetNotificationState action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getId().getAdapterType()}
        });
    }
}