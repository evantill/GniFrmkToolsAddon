package com.gni.frmk.tools.addon.oldies.services;

import com.gni.frmk.tools.addon.action.wm.art.connection.ListAdaptersConnections;
import com.gni.frmk.tools.addon.action.wm.art.listener.ListListeners;
import com.gni.frmk.tools.addon.action.wm.art.notifications.ListNotifications;
import com.gni.frmk.tools.addon.action.wm.art.RetrieveAdapterTypesList;
import com.gni.frmk.tools.addon.action.wm.root.ispackage.PackageList;
import com.gni.frmk.tools.addon.api.action.ActionException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeException;
import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration;
import com.gni.frmk.tools.addon.oldies.invoke.WmArtInvoker;
import com.gni.frmk.tools.addon.oldies.invoke.WmRootInvoker;
import com.gni.frmk.tools.addon.oldies.invoke.WmRootJmsInvoker;
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

    private final WmArtInvoker artInvoker;
    private final WmRootInvoker rootInvoker;
    private final WmRootJmsInvoker rootJmsInvoker;

    public ReportService(WmRootInvoker rootInvoker, WmRootJmsInvoker rootJmsInvoker, WmArtInvoker artInvoker) {
        this.rootInvoker = rootInvoker;
        this.rootJmsInvoker = rootJmsInvoker;
        this.artInvoker = artInvoker;
    }

    public List<ImmutableAdapterConnection> reportAdapterConnectionList() throws InvokeException, ActionException {
        List<ImmutableAdapterConnection> result = Lists.newArrayList();
        for (String type : listAdapterTypes()) {
            result.addAll(listAdapterConnections(type));
        }
        return result;
    }

    public List<ImmutableAdapterListener> reportAdapterListenerList() throws InvokeException, ActionException {
        List<ImmutableAdapterListener> result = Lists.newArrayList();
        for (String type : listAdapterTypes()) {
            result.addAll(listAdapterListeners(type));
        }
        return result;
    }

    public List<ImmutableAdapterNotification> reportAdapterNotificationList() throws InvokeException, ActionException {
        List<ImmutableAdapterNotification> result = Lists.newArrayList();
        for (String type : listAdapterTypes()) {
            result.addAll(listAdapterNotifications(type));
        }
        return result;
    }

    public List<ImmutablePort> reportPortList() throws InvokeException, ActionException {
        return rootInvoker.listPortListeners();
    }

    public List<ImmutableScheduler> reportSchedulerList() throws InvokeException, ActionException {
        return rootInvoker.getUserTaskList();
    }

    public List<ImmutableJmsTrigger> reportJmsTriggerList() throws InvokeException, ActionException {
        return rootJmsInvoker.getJmsTriggerReport();
    }

    public List<ImmutableNativeTrigger> reportNativeTriggerList() throws InvokeException, ActionException {
        return rootInvoker.getNativeTriggerReport();
    }

    public List<ImmutableJmsAlias> reportJmsAliasList() throws InvokeException, ActionException {
        return rootJmsInvoker.getJmsAliasReport();
    }

    public Set<ImmutableIntegrationServerPackage> reportPackages() throws InvokeException, ActionException {
        return rootInvoker.getPackageList(new PackageList());
    }

    public ImmutableConfiguration reportCurrentConfiguration(final String name) throws InvokeException, ActionException {
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

    private List<ImmutableAdapterConnection> listAdapterConnections(String adapterTypeName) throws InvokeException, ActionException {
        return artInvoker.listAdaptersConnections(new ListAdaptersConnections(adapterTypeName));
    }

    private List<ImmutableAdapterListener> listAdapterListeners(String adapterTypeName) throws InvokeException, ActionException {
        return artInvoker.listListeners(new ListListeners(adapterTypeName));
    }

    private List<ImmutableAdapterNotification> listAdapterNotifications(String adapterTypeName) throws InvokeException, ActionException {
        return artInvoker.listNotifications(new ListNotifications(adapterTypeName));
    }
}
