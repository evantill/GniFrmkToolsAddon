package com.gni.frmk.tools.addon.oldies.services;

import com.gni.frmk.tools.addon.action.wm.art.RetrieveAdapterTypesList;
import com.gni.frmk.tools.addon.action.wm.art.connection.ListAdaptersConnections;
import com.gni.frmk.tools.addon.action.wm.art.listener.ListListeners;
import com.gni.frmk.tools.addon.action.wm.art.notifications.ListNotifications;
import com.gni.frmk.tools.addon.api.action.ActionException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeException;
import com.gni.frmk.tools.addon.model.Configuration;
import com.gni.frmk.tools.addon.model.component.*;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 4 oct. 2010
 * Time: 17:29:57
 * To change this template use File | Settings | File Templates.
 */
public class ReportService {

    public List<AdapterConnection> reportAdapterConnectionList() throws InvokeException, ActionException {
        List<AdapterConnection> result = Lists.newArrayList();
        for (String type : listAdapterTypes()) {
            result.addAll(listAdapterConnections(type));
        }
        return result;
    }

    public List<AdapterListener> reportAdapterListenerList() throws InvokeException, ActionException {
        List<AdapterListener> result = Lists.newArrayList();
        for (String type : listAdapterTypes()) {
            result.addAll(listAdapterListeners(type));
        }
        return result;
    }

    public List<AdapterNotification> reportAdapterNotificationList() throws InvokeException, ActionException {
        List<AdapterNotification> result = Lists.newArrayList();
        for (String type : listAdapterTypes()) {
            result.addAll(listAdapterNotifications(type));
        }
        return result;
    }

    public List<Port> reportPortList() throws InvokeException, ActionException {
//        return rootInvoker.listPortListeners();
        return null;
    }

    public List<Scheduler> reportSchedulerList() throws InvokeException, ActionException {
//        return rootInvoker.getUserTaskList();
        return null;
    }

    public List<JmsTrigger> reportJmsTriggerList() throws InvokeException, ActionException {
//        return rootJmsInvoker.getJmsTriggerReport();
        return null;
    }

    public List<NativeTrigger> reportNativeTriggerList() throws InvokeException, ActionException {
//        return rootInvoker.getNativeTriggerReport();
        return null;
    }

    public List<JmsAlias> reportJmsAliasList() throws InvokeException, ActionException {
//        return rootJmsInvoker.getJmsAliasReport();
        return null;
    }


    public Configuration reportCurrentConfiguration(final String name) throws InvokeException, ActionException {
//        return Configuration.builder()
//                            .create(name, new Date())
//                            .addPackages(reportPackages())
//                            .addAdapterConnections(reportAdapterConnectionList())
//                            .addAdapterListeners(reportAdapterListenerList())
//                            .addAdapterNotifications(reportAdapterNotificationList())
//                            .addJmsTriggers(reportJmsTriggerList())
//                            .addJmsAliasConnections(reportJmsAliasList())
//                            .addNativeTriggers(reportNativeTriggerList())
//                            .addPorts(reportPortList())
//                            .addSchedulers(reportSchedulerList())
//                            .build();
        return null;
    }

    private Set<String> listAdapterTypes() throws InvokeException, ActionException {
//        return artInvoker.retrieveAdapterTypesList(new RetrieveAdapterTypesList());
        return null;
    }

    private List<AdapterConnection> listAdapterConnections(String adapterTypeName) throws InvokeException, ActionException {
//        return artInvoker.listAdaptersConnections(new ListAdaptersConnections(adapterTypeName));
          return null;
    }

    private List<AdapterListener> listAdapterListeners(String adapterTypeName) throws InvokeException, ActionException {
//        return artInvoker.listListeners(new ListListeners(adapterTypeName));
        return null;
    }

    private List<AdapterNotification> listAdapterNotifications(String adapterTypeName) throws InvokeException, ActionException {
//        return artInvoker.listNotifications(new ListNotifications(adapterTypeName));
        return null;
    }
}
