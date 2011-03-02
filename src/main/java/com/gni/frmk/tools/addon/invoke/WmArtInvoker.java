package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.IntegrationServerUtil;
import com.gni.frmk.tools.addon.data.adapter.*;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 13 oct. 2010
 * Time: 15:26:38
 * To change this template use File | Settings | File Templates.
 */
public class WmArtInvoker {
    private final ServiceInvoker retrieveAdapterTypesList;
    private final ServiceInvoker enableConnection;
    private final ServiceInvoker disableConnection;
    private final ServiceInvoker listAdapterConnections;
    private final ServiceInvoker enableListener;
    private final ServiceInvoker disableListener;
    private final ServiceInvoker resumeListener;
    private final ServiceInvoker suspendListener;
    private final ServiceInvoker listListeners;
    private final ServiceInvoker enableNotification;
    private final ServiceInvoker disableNotification;
    private final ServiceInvoker resumeNotification;
    private final ServiceInvoker suspendNotification;
    private final ServiceInvoker listNotifications;
    private final IntegrationServerUtil isUtil;

    public WmArtInvoker(IntegrationServerUtil util, ServiceInvokerFactory factory) {
        isUtil = util;
        retrieveAdapterTypesList = factory.createServiceInvokerBuilder("wm.art.admin:retrieveAdapterTypesList")
                .defineOutput("adapterTypes").build();
        enableConnection = factory.createServiceInvokerBuilder("wm.art.admin.connection:enableConnection")
                .defineInput("connectionAlias").build();
        disableConnection = factory.createServiceInvokerBuilder("wm.art.admin.connection:disableConnection")
                .defineInput("connectionAlias").build();
        listAdapterConnections = factory.createServiceInvokerBuilder("pub.art.connection:listAdapterConnections")
                .defineInput("adapterTypeName").defineOutput("connectionDataList").build();
        enableListener = factory.createServiceInvokerBuilder("wm.art.admin.listener:enableListener")
                .defineInput("listenerName").build();
        disableListener = factory.createServiceInvokerBuilder("wm.art.admin.listener:disableListener")
                .defineInput("listenerName").build();
        resumeListener = factory.createServiceInvokerBuilder("wm.art.admin.listener:resumeListener")
                .defineInput("listenerName").build();
        suspendListener = factory.createServiceInvokerBuilder("wm.art.admin.listener:suspendListener")
                .defineInput("listenerName").build();
        listListeners = factory.createServiceInvokerBuilder("pub.art.notification:listAdapterListenerNotifications")
                .defineInput("adapterTypeName").defineOutput("notificationDataList").build();
        enableNotification = factory.createServiceInvokerBuilder("wm.art.admin.notification:enablePollingNotification")
                .defineInput("notificationName").build();
        disableNotification = factory.createServiceInvokerBuilder("wm.art.admin.notification:disablePollingNotification")
                .defineInput("notificationName").build();
        resumeNotification = factory.createServiceInvokerBuilder("wm.art.admin.notification:resumePollingNotification")
                .defineInput("notificationName").build();
        suspendNotification = factory.createServiceInvokerBuilder("wm.art.admin.notification:suspendPollingNotification")
                .defineInput("notificationName").build();
        listNotifications = factory.createServiceInvokerBuilder("pub.art.notification:listAdapterPollingNotifications")
                .defineInput("adapterTypeName").defineOutput("notificationDataList").build();
    }

    public Set<String> retrieveAdapterTypesList() throws ServiceException {
        Set<String> result = new TreeSet<String>();
        Map<String, ?> out = retrieveAdapterTypesList.invoke(ServiceInvoker.EMPTY_DATA);
        if (out.containsKey("adapterTypes")) {
            IData[] dataList = (IData[]) out.get("adapterTypes");
            for (IData type : dataList) {
                IDataCursor curType = type.getCursor();
                try {
                    result.add(IDataUtil.getString(curType, "adapterName"));
                } finally {
                    curType.destroy();
                }
            }
        }
        return result;
    }

    public void enableConnection(String connectionAlias) throws ServiceException {
        enableConnection.invokeSingleInput(connectionAlias);
    }

    public void disableConnection(String connectionAlias) throws ServiceException {
        disableConnection.invokeSingleInput(connectionAlias);
    }

    public Set<AdapterConnection> listAdapterConnections(String adapterTypeName) throws ServiceException {
        Set<AdapterConnection> result = new TreeSet<AdapterConnection>();
        Map<String, ?> out = listAdapterConnections.invokeSingleInput(adapterTypeName);
        if (out.containsKey("connectionDataList")) {
            IData[] dataList = (IData[]) out.get("connectionDataList");
            for (IData doc : dataList) {
                result.add(new AdapterConnectionBuilder(adapterTypeName).define(doc).build());
            }
        }
        return result;
    }

    public void enableListener(String listenerName) throws ServiceException {
        enableListener.invokeSingleInput(listenerName);
    }

    public void disableListener(String listenerName) throws ServiceException {
        disableListener.invokeSingleInput(listenerName);
    }

    public void resumeListener(String listenerName) throws ServiceException {
        resumeListener.invokeSingleInput(listenerName);
    }

    public void suspendListener(String listenerName) throws ServiceException {
        suspendListener.invokeSingleInput(listenerName);
    }

    public Set<AdapterListener> listListeners(String adapterTypeName) throws ServiceException {
        Set<AdapterListener> result = new TreeSet<AdapterListener>();
        Map<String, ?> out = listListeners.invokeSingleInput(adapterTypeName);
        if (out.containsKey("notificationDataList")) {
            IData[] dataList = (IData[]) out.get("notificationDataList");
            for (IData doc : dataList) {
                result.add(new AdapterListenerBuilder(adapterTypeName).define(doc).build());
            }
        }
        return result;
    }

    public void enableNotification(String notificationName) throws ServiceException {
        enableNotification.invokeSingleInput(notificationName);
    }

    public void disableNotification(String notificationName) throws ServiceException {
        disableNotification.invokeSingleInput(notificationName);
    }

    public void resumeNotification(String notificationName) throws ServiceException {
        resumeNotification.invokeSingleInput(notificationName);
    }

    public void suspendNotification(String notificationName) throws ServiceException {
        suspendNotification.invokeSingleInput(notificationName);
    }

    public Set<AdapterNotification> listNotifications(String adapterTypeName) throws ServiceException {
        Set<AdapterNotification> result = new TreeSet<AdapterNotification>();
        Map<String, ?> out = listNotifications.invokeSingleInput(adapterTypeName);
        if (out.containsKey("notificationDataList")) {
            IData[] dataList = (IData[]) out.get("notificationDataList");
            for (IData doc : dataList) {
                result.add(new AdapterNotificationBuilder(adapterTypeName).define(doc).build());
            }
        }
        return result;
    }

    public boolean isEnabled() {
        return isUtil.isPackageEnabled("WmART");
    }
}
