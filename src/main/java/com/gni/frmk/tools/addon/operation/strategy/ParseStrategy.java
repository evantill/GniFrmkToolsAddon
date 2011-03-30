package com.gni.frmk.tools.addon.operation.strategy;

import com.gni.frmk.tools.addon.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.configuration.Configuration;
import com.gni.frmk.tools.addon.configuration.component.*;
import com.gni.frmk.tools.addon.operation.visitor.ConfigurationVisitor;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */
public class ParseStrategy implements ConfigurationVisitorStrategy {

    private final ConfigurationVisitor visitor;

    public ParseStrategy(ConfigurationVisitor visitor) {
        this.visitor = visitor;
    }

    public void execute(Configuration cnf) {
        for (ComponentConfiguration<AdapterConnection, EnableState> element : cnf.getAdapterConnectionConfigurations()) {
            element.getComponent().accept(visitor);
        }
        for (ComponentConfiguration<AdapterNotification, ActivableState> element : cnf.getAdapterNotificationConfigurations()) {
            element.getComponent().accept(visitor);
        }
        for (ComponentConfiguration<AdapterListener, ActivableState> element : cnf.getAdapterListenerConfigurations()) {
            element.getComponent().accept(visitor);
        }
        for (ComponentConfiguration<Port, ActivableState> element : cnf.getPortConfigurations()) {
            element.getComponent().accept(visitor);
        }
        for (ComponentConfiguration<Scheduler, SchedulerState> element : cnf.getSchedulerConfigurations()) {
            element.getComponent().accept(visitor);
        }
        for (ComponentConfiguration<JmsTrigger, ActivableState> element : cnf.getJmsTriggerConfigurations()) {
            element.getComponent().accept(visitor);
        }
        for (ComponentConfiguration<NativeTrigger, NativeTriggerState> element : cnf.getNativeTriggerConfigurations()) {
            element.getComponent().accept(visitor);
        }
        for (ComponentConfiguration<JmsAlias, ConnectableState> element : cnf.getJmsAliasConfigurations()) {
            element.getComponent().accept(visitor);
        }
    }
}
