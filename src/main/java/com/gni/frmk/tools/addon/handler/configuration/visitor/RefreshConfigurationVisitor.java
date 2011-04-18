package com.gni.frmk.tools.addon.handler.configuration.visitor;

import com.gni.frmk.tools.addon.action.wm.art.connection.ListAdaptersConnections;
import com.gni.frmk.tools.addon.action.wm.art.listener.ListListeners;
import com.gni.frmk.tools.addon.action.wm.art.notifications.ListNotifications;
import com.gni.frmk.tools.addon.action.wm.jms.alias.GetJmsAliasReport;
import com.gni.frmk.tools.addon.action.wm.jms.trigger.GetJmsTriggerReport;
import com.gni.frmk.tools.addon.action.wm.root.ispackage.PackageList;
import com.gni.frmk.tools.addon.action.wm.root.port.ListPortListeners;
import com.gni.frmk.tools.addon.action.wm.root.scheduler.GetUserTaskList;
import com.gni.frmk.tools.addon.action.wm.root.trigger.GetNativeTriggerReport;
import com.gni.frmk.tools.addon.api.Producer;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.CollectionResult;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisited;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.api.Component;
import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.component.*;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 13/04/11
 * Time: 18:33
 *
 * @author: e03229
 */
public class RefreshConfigurationVisitor
        implements
        ConfigurationVisitor,
        Producer<List<Action<? extends CollectionResult<? extends Collection<? extends Component>, ? extends Component>>>> {

    private final List<Action<? extends CollectionResult<? extends Collection<? extends Component>, ? extends Component>>> actions = Lists
            .newArrayList();

    @Override
    public List<Action<? extends CollectionResult<? extends Collection<? extends Component>, ? extends Component>>> produce() {
        return Collections.unmodifiableList(actions);
    }

    @Override
    public void dispatchVisit(ConfigurationVisited visitable) {
        actions.clear();
        visitable.accept(this);
    }

    @Override
    public void visitConfiguration(Configuration configuration) {
    }

    @Override
    public void visitComponent(AdapterConnection visited) {
        final String adapterType = visited.getAdapterType();
        ListAdaptersConnections action = new ListAdaptersConnections(adapterType);
        action.addToCollection(visited);
        addAction(action);
    }

    @Override
    public void visitComponent(AdapterListener visited) {
        final String adapterType = visited.getAdapterType();
        ListListeners action = new ListListeners(adapterType);
        action.addToCollection(visited);
        addAction(action);
    }

    @Override
    public void visitComponent(AdapterNotification visited) {
        final String adapterType = visited.getAdapterType();
        ListNotifications action = new ListNotifications(adapterType);
        action.addToCollection(visited);
        addAction(action);
    }

    @Override
    public void visitComponent(Port visited) {
        ListPortListeners action = new ListPortListeners();
        action.addToCollection(visited);
        addAction(action);
    }

    @Override
    public void visitComponent(Scheduler visited) {
        GetUserTaskList action = new GetUserTaskList();
        action.addToCollection(visited);
        addAction(action);
    }

    @Override
    public void visitComponent(NativeTrigger visited) {
        GetNativeTriggerReport action = new GetNativeTriggerReport();
        action.addToCollection(visited);
        addAction(action);
    }

    @Override
    public void visitComponent(IntegrationServerPackage visited) {
        PackageList action = new PackageList();
        action.addToCollection(visited);
        addAction(action);
    }

    @Override
    public void visitComponent(JmsTrigger visited) {
        GetJmsTriggerReport action = new GetJmsTriggerReport();
        action.addToCollection(visited);
        addAction(action);
    }

    @Override
    public void visitComponent(JmsAlias visited) {
        GetJmsAliasReport action = new GetJmsAliasReport();
        action.addToCollection(visited);
        addAction(action);
    }

    @Override
    public void visitComponentConfiguration(AdapterConnectionConfiguration visited) {
    }

    @Override
    public void visitComponentConfiguration(AdapterListenerConfiguration visited) {
    }

    @Override
    public void visitComponentConfiguration(AdapterNotificationConfiguration visited) {
    }

    @Override
    public void visitComponentConfiguration(PortConfiguration visited) {
    }

    @Override
    public void visitComponentConfiguration(SchedulerConfiguration visited) {
    }

    @Override
    public void visitComponentConfiguration(NativeTriggerConfiguration visited) {
    }

    @Override
    public void visitComponentConfiguration(JmsTriggerConfiguration visited) {
    }

    @Override
    public void visitComponentConfiguration(JmsAliasConfiguration visited) {
    }

    @Override
    public void visitComponentConfiguration(IntegrationServerPackageConfiguration visited) {
    }

    private void addAction(Action<? extends CollectionResult<? extends Collection<? extends Component>, ? extends Component>> action) {
        actions.add(action);
    }
}
