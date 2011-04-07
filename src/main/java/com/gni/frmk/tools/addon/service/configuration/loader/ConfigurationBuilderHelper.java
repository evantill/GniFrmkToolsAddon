package com.gni.frmk.tools.addon.service.configuration.loader;

import com.gni.frmk.tools.addon.model.component.AbstractComponent;
import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.model.configuration.Configuration.Builder;
import com.gni.frmk.tools.addon.model.configuration.component.*;
import com.gni.frmk.tools.addon.service.api.configuration.ComponentConfigurationVisited;
import com.gni.frmk.tools.addon.service.api.configuration.ComponentConfigurationVisitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 18:40
 *
 * @author: e03229
 */
class ConfigurationBuilderHelper implements ComponentConfigurationVisitor {

    private final Builder builder;

    public ConfigurationBuilderHelper(Builder builder) {this.builder = builder;}

    @Override
    public void dispatchVisit(ComponentConfigurationVisited visitable) {
        visitable.accept(this);
    }

    @Override
    public void visit(AdapterConnectionConfiguration visited) {
        builder.addAdapterConnection(visited);
    }

    @Override
    public void visit(AdapterListenerConfiguration visited) {
        builder.addAdapterListener(visited);
    }

    @Override
    public void visit(AdapterNotificationConfiguration visited) {
        builder.addAdapterNotification(visited);
    }

    @Override
    public void visit(PortConfiguration visited) {
        builder.addPort(visited);
    }

    @Override
    public void visit(SchedulerConfiguration visited) {
        builder.addScheduler(visited);
    }

    @Override
    public void visit(NativeTriggerConfiguration visited) {
        builder.addNativeTrigger(visited);
    }

    @Override
    public void visit(JmsTriggerConfiguration visited) {
        builder.addJmsTrigger(visited);
    }

    @Override
    public void visit(JmsAliasConfiguration visited) {
        builder.addJmsAliasConnection(visited);
    }

    @Override
    public void visit(IntegrationServerPackageConfiguration visited) {
        builder.addPackage(visited);
    }
}
