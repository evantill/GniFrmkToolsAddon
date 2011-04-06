package com.gni.frmk.tools.addon.service.configuration.strategy;

import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.service.api.configuration.ComponentConfigurationVisitor;
import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationProcessingStrategy;
import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationVisited;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 20:33
 *
 * @author: e03229
 */
public class ParseStrategy implements ConfigurationProcessingStrategy {

    @Override
    public ConfigurationProcessingStrategy execute(Operation o) {
        ConfigurationVisited cnf = o.getVisited();
        ComponentConfigurationVisitor visitor = o.getVisitor();
        for (ComponentConfiguration<AdapterListener, ActivableState> element : cnf.getAdapterListenerConfigurations()) {
            element.accept(visitor);
        }
        for (ComponentConfiguration<AdapterNotification, ActivableState> element : cnf.getAdapterNotificationConfigurations()) {
            element.accept(visitor);
        }
        for (ComponentConfiguration<NativeTrigger, NativeTriggerState> element : cnf.getNativeTriggerConfigurations()) {
            element.accept(visitor);
        }
        for (ComponentConfiguration<JmsTrigger, ActivableState> element : cnf.getJmsTriggerConfigurations()) {
            element.accept(visitor);
        }

        for (ComponentConfiguration<Scheduler, SchedulerState> element : cnf.getSchedulerConfigurations()) {
            element.accept(visitor);
        }
        for (ComponentConfiguration<Port, ActivableState> element : cnf.getPortConfigurations()) {
            element.accept(visitor);
        }
        for (ComponentConfiguration<AdapterConnection, EnableState> element : cnf.getAdapterConnectionConfigurations()) {
            element.accept(visitor);
        }
        for (ComponentConfiguration<JmsAlias, ConnectableState> element : cnf.getJmsAliasConfigurations()) {
            element.accept(visitor);
        }
        return this;
    }

}