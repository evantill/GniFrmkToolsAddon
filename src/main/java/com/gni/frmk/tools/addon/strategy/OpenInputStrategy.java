package com.gni.frmk.tools.addon.strategy;

import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.visitor.api.ConfigurationVisitor;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:37
 * To change this template use File | Settings | File Templates.
 */
public class OpenInputStrategy implements ConfigurationVisitorStrategy {

    private final ConfigurationVisitor visitor;

    public OpenInputStrategy(ConfigurationVisitor visitor) {
        this.visitor = visitor;
    }

    public void execute(Configuration cnf) {
        for (ComponentConfiguration<AdapterListener, ActivableState> element : cnf.getAdapterListenerConfigurations()) {
            element.getComponent().accept(visitor);
        }
        for (ComponentConfiguration<AdapterNotification, ActivableState> element : cnf.getAdapterNotificationConfigurations()) {
            element.getComponent().accept(visitor);
        }
        for (ComponentConfiguration<NativeTrigger, NativeTriggerState> element : cnf.getNativeTriggerConfigurations()) {
            element.getComponent().accept(visitor);
        }
        for (ComponentConfiguration<JmsTrigger, ActivableState> element : cnf.getJmsTriggerConfigurations()) {
            element.getComponent().accept(visitor);
        }

        for (ComponentConfiguration<Scheduler, SchedulerState> element : cnf.getSchedulerConfigurations()) {
            element.getComponent().accept(visitor);
        }
        for (ComponentConfiguration<Port, ActivableState> element : cnf.getPortConfigurations()) {
            element.getComponent().accept(visitor);
        }
    }
}