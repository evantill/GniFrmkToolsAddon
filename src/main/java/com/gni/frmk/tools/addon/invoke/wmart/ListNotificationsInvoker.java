package com.gni.frmk.tools.addon.invoke.wmart;

import com.gni.frmk.tools.addon.configuration.components.ActivableState;
import com.gni.frmk.tools.addon.configuration.components.AdapterNotification;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.ListResult;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionHandler;
import com.google.common.collect.Lists;
import com.wm.data.*;

import java.util.List;

import static com.gni.frmk.tools.addon.invoke.wmart.ListenerNotificationUtils.defineState;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:23
 *
 * @author: e03229
 */
 class ListNotificationsInvoker extends AdapterTypeAwareAction<ListNotifications, ListResult<AdapterNotification>>
        implements ActionHandler<ListNotifications, ListResult<AdapterNotification>, InvokeContext> {

    public ListNotificationsInvoker() {
        super("pub.art.notification:listAdapterPollingNotifications");
    }

    @Override
    public Class<ListNotifications> getActionType() {
        return ListNotifications.class;
    }

    @Override
    protected ListResult<AdapterNotification> parseOutput(IData output) {
        IDataCursor cur = output.getCursor();
        try {
            List<AdapterNotification> values = Lists.newArrayList();
            IData[] dataList = IDataUtil.getIDataArray(cur, "notificationDataList");
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        ActivableState state = defineState(IDataUtil.getString(curLoop, "notificationEnabled"));
                        values.add(AdapterNotification.builder()
                                                      .name(IDataUtil.getString(curLoop, "notificationNodeName"))
                                                      .adapterType(IDataUtil.getString(curLoop, "name"))
                                                      .packageName(IDataUtil.getString(curLoop, "packageName"))
                                                      .defineState(state)
                                                      .build());
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<AdapterNotification>(values);
        } finally {
            cur.destroy();
        }

    }
}
