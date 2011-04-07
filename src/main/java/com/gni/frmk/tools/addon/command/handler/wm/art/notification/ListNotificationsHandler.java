package com.gni.frmk.tools.addon.command.handler.wm.art.notification;

import com.gni.frmk.tools.addon.command.action.wm.art.notifications.ListNotifications;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.handler.wm.art.AdapterTypeAwareHandler;
import com.gni.frmk.tools.addon.command.result.ListResult;
import com.gni.frmk.tools.addon.model.component.AdapterNotification;
import com.gni.frmk.tools.addon.model.component.AdapterNotification.AdapterNotificationBuilder;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.google.common.collect.Maps;
import com.wm.data.*;

import java.util.Map;

import static com.gni.frmk.tools.addon.command.handler.wm.art.ListenerNotificationUtils.defineState;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:23
 *
 * @author: e03229
 */
public class ListNotificationsHandler extends AdapterTypeAwareHandler<ListNotifications, ListResult<AdapterNotification>>
        implements ActionHandler<ListNotifications, ListResult<AdapterNotification>, InvokeContext> {

    public ListNotificationsHandler() {
        super("pub.art.notification:listAdapterPollingNotifications");
    }

    @Override
    public Class<ListNotifications> getActionType() {
        return ListNotifications.class;
    }

    @Override
    protected ListResult<AdapterNotification> parseOutput(ListNotifications action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            Map<String, AdapterNotification> values = Maps.newHashMap();
            for (AdapterNotification notification : action.getCollection()) {
                values.put(notification.getComponentId().asString(), notification);
            }
            IData[] dataList = IDataUtil.getIDataArray(cur, "notificationDataList");
            String adapterType = action.getParameter();
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String notificationNodeName = IDataUtil.getString(curLoop, "notificationNodeName");
                        AdapterNotification value = values.get(notificationNodeName);
                        if (value == null && action.isUpdate()) {
                            continue;
                        }
                        AdapterNotificationBuilder builder = AdapterNotification.builder();
                        if (action.isUpdate()) {
                            builder.from(value);
                        } else {
                            builder.name(notificationNodeName)
                                   .adapterType(adapterType)
                                   .packageName(IDataUtil.getString(curLoop, "packageName"));
                        }
                        ActivableState state = defineState(IDataUtil.getString(curLoop, "notificationEnabled"));
                        builder.defineState(state);
                        //add result
                        value = builder.build();
                        values.put(value.getComponentId().asString(), value);
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<AdapterNotification>(values.values());
        } finally {
            cur.destroy();
        }

    }
}
