package com.gni.frmk.tools.addon.service;

import com.gni.frmk.tools.addon.data.Configuration;
import com.gni.frmk.tools.addon.data.adapter.AdapterConnection;
import com.gni.frmk.tools.addon.data.adapter.AdapterListener;
import com.gni.frmk.tools.addon.data.adapter.AdapterNotification;
import com.gni.frmk.tools.addon.data.port.Port;
import com.gni.frmk.tools.addon.data.scheduler.Scheduler;
import com.gni.frmk.tools.addon.data.trigger.JmsAlias;
import com.gni.frmk.tools.addon.data.trigger.JmsTrigger;
import com.gni.frmk.tools.addon.data.trigger.NativeTrigger;
import com.gni.frmk.tools.addon.invoke.ServiceException;
import com.gni.frmk.tools.addon.invoke.divers.WmArtInvoker;
import com.gni.frmk.tools.addon.invoke.divers.WmRootJmsInvoker;
import com.gni.frmk.tools.addon.invoke.divers.WmRootNativeInvoker;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 4 oct. 2010
 * Time: 17:29:57
 * To change this template use File | Settings | File Templates.
 */
public class ReportService {

    private final WmArtInvoker artInvoker;
    private final WmRootNativeInvoker rootInvoker;
    private final WmRootJmsInvoker rootJmsInvoker;

    public ReportService(WmRootNativeInvoker rootInvoker, WmRootJmsInvoker rootJmsInvoker, WmArtInvoker artInvoker) {
        this.rootInvoker = rootInvoker;
        this.rootJmsInvoker = rootJmsInvoker;
        this.artInvoker = artInvoker;
    }

    public Set<AdapterConnection> reportAdapterConnectionList() throws ServiceException {
        Set<AdapterConnection> result = new TreeSet<AdapterConnection>();
        for (String type : listAdapterTypes()) {
            result.addAll(listAdapterConnections(type));
        }
        return result;
    }

    public Set<AdapterListener> reportAdapterListenerList() throws ServiceException {
        Set<AdapterListener> result = new TreeSet<AdapterListener>();
        for (String type : listAdapterTypes()) {
            result.addAll(listAdapterListeners(type));
        }
        return result;
    }

    public Set<AdapterNotification> reportAdapterNotificationList() throws ServiceException {
        Set<AdapterNotification> result = new TreeSet<AdapterNotification>();
        for (String type : listAdapterTypes()) {
            result.addAll(listAdapterNotifications(type));
        }
        return result;
    }

    public Set<Port> reportPortList() throws ServiceException {
        return rootInvoker.listPortListeners();
    }

    public Set<Scheduler> reportSchedulerList() throws ServiceException {
        return rootInvoker.getUserTaskList();
    }

    public Set<JmsTrigger> reportJmsTriggerList() throws ServiceException {
        return rootJmsInvoker.getTriggerReport();
    }

    public Set<NativeTrigger> reportNativeTriggerList() throws ServiceException {
        return rootInvoker.getTriggerReport();
    }

    public Set<JmsAlias> reportJmsAliasList() throws ServiceException {
        return rootJmsInvoker.getConnectionAliasReport();
    }

    public Configuration reportCurrentConfiguration(final String name) throws ServiceException {
        Configuration result = new Configuration(name);
        result.setAdapterConnectionList(reportAdapterConnectionList());
        result.setAdapterListenerList(reportAdapterListenerList());
        result.setAdapterNotificationList(reportAdapterNotificationList());
        result.setJmsTriggerList(reportJmsTriggerList());
        result.setJmsAliasList(reportJmsAliasList());
        result.setNativeTriggerList(reportNativeTriggerList());
        result.setPortList(reportPortList());
        result.setSchedulerList(reportSchedulerList());
        return result;
    }

    private Set<String> listAdapterTypes() throws ServiceException {
        return artInvoker.retrieveAdapterTypesList();
    }

    private Set<AdapterConnection> listAdapterConnections(String adapterTypeName) throws ServiceException {
        return artInvoker.listAdapterConnections(adapterTypeName);
    }

    private Set<AdapterListener> listAdapterListeners(String adapterTypeName) throws ServiceException {
        return artInvoker.listListeners(adapterTypeName);
    }

    private Set<AdapterNotification> listAdapterNotifications(String adapterTypeName) throws ServiceException {
        return artInvoker.listNotifications(adapterTypeName);
    }
}
