package com.gni.frmk.tools.addon.data.adapter;

import com.gni.frmk.tools.addon.data.component.ActivableComponentState;
import com.gni.frmk.tools.addon.data.component.ComponentBuilder;
import com.gni.frmk.tools.addon.data.component.ComponentState;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import static com.gni.frmk.tools.addon.data.component.ActivableComponentState.ActiveStatus.ACTIVE;
import static com.gni.frmk.tools.addon.data.component.ActivableComponentState.ActiveStatus.SUSPENDED;
import static com.gni.frmk.tools.addon.data.component.ComponentState.EnableStatus.DISABLED;
import static com.gni.frmk.tools.addon.data.component.ComponentState.EnableStatus.ENABLED;
import static com.gni.frmk.tools.addon.data.component.ComponentType.ADAPTER_NOTIFICATION;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 29 sept. 2010
 * Time: 19:00:27
 * To change this template use File | Settings | File Templates.
 */
public class AdapterNotificationBuilder extends AdapterBuilder<AdapterNotification, AdapterNotificationInfo, ActivableComponentState> {

    public static final class CustomXmlAdapter extends XmlAdapter<ComponentBuilder<AdapterNotification, AdapterNotificationInfo, ActivableComponentState>, AdapterNotification> {
        @Override
        public AdapterNotification unmarshal(ComponentBuilder<AdapterNotification, AdapterNotificationInfo, ActivableComponentState> v) throws Exception {
            return v.build();
        }

        @Override
        public ComponentBuilder<AdapterNotification, AdapterNotificationInfo, ActivableComponentState> marshal(AdapterNotification v) throws Exception {
            return new AdapterNotificationBuilder(v.getInfos().getAdapterType()).define(v);
        }
    }

    ActivableComponentState.EnableStatus enableStatus;
    ActivableComponentState.ActiveStatus activeStatus;
    String name;
    String packageName;

    public AdapterNotificationBuilder(String adapterType) {
        super(adapterType);
    }

    public ComponentBuilder<AdapterNotification, AdapterNotificationInfo, ActivableComponentState> define(String name,
                                                                                                          String packageName,
                                                                                                          ComponentState.EnableStatus enableStatus,
                                                                                                          ActivableComponentState.ActiveStatus activeStatus) {
        this.enableStatus = enableStatus;
        this.activeStatus = activeStatus;
        this.name = name;
        this.packageName = packageName;
        return this;
    }

    /**
     * use pipeline data from service pub.art.notification:listAdapterPollingNotifications
     *
     * @param doc the datas of one notification
     * @return builder for chain calling
     */
    public ComponentBuilder<AdapterNotification, AdapterNotificationInfo, ActivableComponentState> define(IData doc) {
        IDataCursor curDoc = doc.getCursor();
        try {
            name = checkNotNull(IDataUtil.getString(curDoc, "notificationNodeName"));
            packageName = checkNotNull(IDataUtil.getString(curDoc, "packageName"));
            String notificationEnabled = checkNotNull(IDataUtil.getString(curDoc, "notificationEnabled"));
            if ("yes".equals(notificationEnabled)) {
                enableStatus = ENABLED;
                activeStatus = ACTIVE;
            } else if ("no".equals(notificationEnabled)) {
                enableStatus = DISABLED;
                activeStatus = ACTIVE;
            } else if ("suspended".equals(notificationEnabled)) {
                enableStatus = ENABLED;
                activeStatus = SUSPENDED;
            } else if ("unsched".equals(notificationEnabled)) {
                enableStatus = DISABLED;
                activeStatus = SUSPENDED;
            } else {
                throw new IllegalStateException(String.format("unknown notification %s state %s", name, notificationEnabled));
            }
            checkNotNull(enableStatus);
            checkNotNull(activeStatus);
            return this;
        } finally {
            curDoc.destroy();
        }
    }

    @Override
    public AdapterNotification build() {
        this.defineType(ADAPTER_NOTIFICATION)
                .defineId(name)
                .defineState(new ActivableComponentState(enableStatus, activeStatus))
                .defineInfo(new AdapterNotificationInfo(adapterType, packageName, name));
        return new AdapterNotification(this);
    }
}
