package com.gni.frmk.tools.addon.operation.handler.component.art.notification.oldies;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotificationDetail;
import com.gni.frmk.tools.addon.operation.action.component.art.notifications.oldies.GetAdapterNotificationDetail;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.handler.component.oldies.GetComponentDetailHandler;
import com.gni.frmk.tools.addon.operation.result.SingleResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:06
 *
 * @author: e03229
 */
public class GetAdapterNotificationDetailHandler
        extends AbstractInvokeHandler<GetAdapterNotificationDetail, SingleResult<AdapterNotificationDetail>>
        implements GetComponentDetailHandler<GetAdapterNotificationDetail, AdapterId, AdapterNotificationDetail, InvokeContext> {

    public GetAdapterNotificationDetailHandler() {
        super("pub.art.notification:listAdapterPollingNotifications");
    }

    @Override
    public Class<GetAdapterNotificationDetail> getActionType() {
        return GetAdapterNotificationDetail.class;
    }

    @Override
    protected SingleResult<AdapterNotificationDetail> parseOutput(GetAdapterNotificationDetail action, IData output) {
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
                        String packageName = IDataUtil.getString(curLoop, "packageName");
                        return new SingleResult<AdapterNotificationDetail>(new AdapterNotificationDetail(packageName));
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new SingleResult<AdapterNotificationDetail>(new AdapterNotificationDetail());
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(GetAdapterNotificationDetail action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getId().getAdapterType()}
        });
    }
}