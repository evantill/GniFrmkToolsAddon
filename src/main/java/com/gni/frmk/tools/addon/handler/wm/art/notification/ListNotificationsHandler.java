package com.gni.frmk.tools.addon.handler.wm.art.notification;

import com.gni.frmk.tools.addon.action.wm.art.notifications.ListNotifications;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.art.AdapterTypeAwareHandler;
import com.gni.frmk.tools.addon.model.component.ImmutableAdapterNotification.MutableAdapterNotification;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.result.ListResult;
import com.google.common.collect.Maps;
import com.wm.data.*;

import java.util.Map;

import static com.gni.frmk.tools.addon.handler.wm.art.ListenerNotificationUtils.defineState;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:23
 *
 * @author: e03229
 */
public class ListNotificationsHandler extends AdapterTypeAwareHandler<ListNotifications, ListResult<MutableAdapterNotification>>
        implements ActionHandler<ListNotifications, ListResult<MutableAdapterNotification>, InvokeContext> {

    public ListNotificationsHandler() {
        super("pub.art.notification:listAdapterPollingNotifications");
    }

    @Override
    public Class<ListNotifications> getActionType() {
        return ListNotifications.class;
    }

    @Override
    protected ListResult<MutableAdapterNotification> parseOutput(ListNotifications action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            Map<String, MutableAdapterNotification> values = Maps.newHashMap();
            for (MutableAdapterNotification notification : action.getCollection()) {
                values.put(notification.getComponentId().asString(), notification);
            }
            IData[] dataList = IDataUtil.getIDataArray(cur, "notificationDataList");
            String adapterType = action.getParameter();
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String notificationNodeName = IDataUtil.getString(curLoop, "notificationNodeName");
                        MutableAdapterNotification value = values.get(notificationNodeName);
                        if (value == null && action.isUpdate()) {
                            continue;
                        } else if (value == null && !action.isUpdate()) {
                            value = new MutableAdapterNotification();
                            value.setAdapterType(adapterType);
                            value.setPackageName(IDataUtil.getString(curLoop, "packageName"));
                        }

                        ActivableState state = defineState(IDataUtil.getString(curLoop, "notificationEnabled"));
                        value.setState(state);
                        values.put(value.getComponentId().asString(), value);
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<MutableAdapterNotification>(values.values());
        } finally {
            cur.destroy();
        }

    }
}
