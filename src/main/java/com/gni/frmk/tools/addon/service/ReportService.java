package com.gni.frmk.tools.addon.service;

import com.gni.frmk.tools.addon.configuration.Configuration;
import com.gni.frmk.tools.addon.configuration.components.*;
import com.gni.frmk.tools.addon.dispatcher.ActionException;
import com.gni.frmk.tools.addon.invoke.actions.wmart.ListNotifications;
import com.gni.frmk.tools.addon.invoke.actions.wmart.RetrieveAdapterTypesList;
import com.gni.frmk.tools.addon.invoke.InvokeException;
import com.gni.frmk.tools.addon.invoke.WmArtInvoker;
import com.gni.frmk.tools.addon.invoke.WmRootInvoker;
import com.gni.frmk.tools.addon.invoke.WmRootJmsInvoker;
import com.gni.frmk.tools.addon.invoke.actions.wmart.ListAdaptersConnections;
import com.gni.frmk.tools.addon.invoke.actions.wmart.ListListeners;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.PackageList;
import com.google.common.collect.Lists;

import java.util.Date;
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

    private final WmArtInvoker artInvoker;
    private final WmRootInvoker rootInvoker;
    private final WmRootJmsInvoker rootJmsInvoker;

    public ReportService(WmRootInvoker rootInvoker, WmRootJmsInvoker rootJmsInvoker, WmArtInvoker artInvoker) {
        this.rootInvoker = rootInvoker;
        this.rootJmsInvoker = rootJmsInvoker;
        this.artInvoker = artInvoker;
    }

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
        return rootInvoker.listPortListeners();
    }

    public List<Scheduler> reportSchedulerList() throws InvokeException, ActionException {
        return rootInvoker.getUserTaskList();
    }

    public List<JmsTrigger> reportJmsTriggerList() throws InvokeException, ActionException {
        return rootJmsInvoker.getJmsTriggerReport();
    }

    public List<NativeTrigger> reportNativeTriggerList() throws InvokeException, ActionException {
        return rootInvoker.getNativeTriggerReport();
    }

    public List<JmsAlias> reportJmsAliasList() throws InvokeException, ActionException {
        return rootJmsInvoker.getJmsAliasReport();
    }

    public Set<IntegrationServerPackage> reportPackages() throws InvokeException, ActionException {
        return rootInvoker.getPackageList(new PackageList());
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
        return artInvoker.retrieveAdapterTypesList(new RetrieveAdapterTypesList());
    }

    private List<AdapterConnection> listAdapterConnections(String adapterTypeName) throws InvokeException, ActionException {
        return artInvoker.listAdaptersConnections(new ListAdaptersConnections(adapterTypeName));
    }

    private List<AdapterListener> listAdapterListeners(String adapterTypeName) throws InvokeException, ActionException {
        return artInvoker.listListeners(new ListListeners(adapterTypeName));
    }

    private List<AdapterNotification> listAdapterNotifications(String adapterTypeName) throws InvokeException, ActionException {
        return artInvoker.listNotifications(new ListNotifications(adapterTypeName));
    }
}
