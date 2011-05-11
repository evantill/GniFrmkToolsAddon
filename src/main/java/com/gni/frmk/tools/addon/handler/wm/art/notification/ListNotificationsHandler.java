package com.gni.frmk.tools.addon.handler.wm.art.notification;

import com.gni.frmk.tools.addon.action.wm.art.notifications.ListNotifications;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.id.AdapterId;
import com.gni.frmk.tools.addon.result.ListResult;
import com.google.common.collect.Lists;
import com.wm.data.*;
import com.gni.frmk.tools.addon.api.action.ActionHandler;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:23
 *
 * @author: e03229
 */
public class ListNotificationsHandler
        extends AbstractInvokeHandler<ListNotifications, ListResult<AdapterId>>
        implements ActionHandler<ListNotifications, ListResult<AdapterId>, InvokeContext> {

    public ListNotificationsHandler() {
        super("pub.art.notification:listAdapterPollingNotifications");
    }

    @Override
    public Class<ListNotifications> getActionType() {
        return ListNotifications.class;
    }

    @Override
    protected IData prepareInput(ListNotifications action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"adapterTypeName",
                 action.getAdapterType()}
        });
    }


    @Override
    protected ListResult<AdapterId> parseOutput(ListNotifications action, IData output) {
        //TODO TESTER chez ALU sur les SAP Listeners
        IDataCursor cur = output.getCursor();
        try {
            IData[] dataList = IDataUtil.getIDataArray(cur, "notificationDataList");
            final String adapterType = action.getAdapterType();
            final List<AdapterId> result = Lists.newArrayList();
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String listenerNodeName = IDataUtil.getString(curLoop, "notificationNodeName");
                        result.add(new AdapterId(listenerNodeName, adapterType));
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<AdapterId>(result);
        } finally {
            cur.destroy();
        }
    }
}
