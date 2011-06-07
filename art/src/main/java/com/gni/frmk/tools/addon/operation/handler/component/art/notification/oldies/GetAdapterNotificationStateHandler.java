package com.gni.frmk.tools.addon.operation.handler.component.art.notification.oldies;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.notifications.oldies.GetAdapterNotificationState;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.handler.component.oldies.GetComponentStateHandler;
import com.gni.frmk.tools.addon.operation.result.SingleResult;
import com.wm.data.*;

import static com.gni.frmk.tools.addon.operation.handler.component.art.ListenerNotificationUtils.defineState;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:06
 *
 * @author: e03229
 */
public class GetAdapterNotificationStateHandler
        extends AbstractInvokeHandler<GetAdapterNotificationState, SingleResult<ActivableState>>
        implements GetComponentStateHandler<GetAdapterNotificationState, AdapterId, ActivableState, InvokeContext> {

    public GetAdapterNotificationStateHandler() {
        super("pub.art.notification:listAdapterPollingNotifications");
    }

    @Override
    public Class<GetAdapterNotificationState> getActionType() {
        return GetAdapterNotificationState.class;
    }

    @Override
    protected SingleResult<ActivableState> parseOutput(GetAdapterNotificationState action, IData output) {
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
    protected IData prepareInput(GetAdapterNotificationState action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getId().getAdapterType()}
        });
    }
}
