package com.gni.frmk.tools.addon.handler.component.art.notification;

import com.gni.frmk.tools.addon.action.component.art.notifications.GetNotificationDetail;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotification.AdapterNotificationDetail;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 19:06
 *
 * @author: e03229
 */
public class GetNotificationDetailHandler extends AbstractInvokeHandler<GetNotificationDetail, ComponentDetailResult<AdapterNotificationDetail>>
        implements ActionHandler<GetNotificationDetail, ComponentDetailResult<AdapterNotificationDetail>, InvokeContext> {


    public GetNotificationDetailHandler() {
        super("pub.art.notification:listAdapterPollingNotifications");
    }

    @Override
    public Class<GetNotificationDetail> getActionType() {
        return GetNotificationDetail.class;
    }

    @Override
    protected ComponentDetailResult<AdapterNotificationDetail> parseOutput(GetNotificationDetail action, IData output) {
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
                        return new ComponentDetailResult<AdapterNotificationDetail>(new AdapterNotificationDetail(packageName));
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ComponentDetailResult<AdapterNotificationDetail>(new AdapterNotificationDetail());
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(GetNotificationDetail action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getId().getAdapterType()}
        });
    }
}