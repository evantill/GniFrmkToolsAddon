package com.gni.frmk.tools.addon.service.configuration.strategy;

import com.gni.frmk.tools.addon.model.component.*;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.model.configuration.component.*;
import com.gni.frmk.tools.addon.service.api.configuration.ComponentConfigurationVisitor;
import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationProcessingStrategy;
import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationVisited;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 20:31
 *
 * @author: e03229
 */
public abstract class InputOutputStrategy implements ConfigurationProcessingStrategy {

    protected void processInput(Operation o) {
        ConfigurationVisited cnf = o.getVisited();
        ComponentConfigurationVisitor visitor = o.getVisitor();
        for (AdapterListenerConfiguration element : cnf.getAdapterListenerConfigurations()) {
            element.accept(visitor);
        }
        for (AdapterNotificationConfiguration element : cnf.getAdapterNotificationConfigurations()) {
            element.accept(visitor);
        }
        for (NativeTriggerConfiguration element : cnf.getNativeTriggerConfigurations()) {
            element.accept(visitor);
        }
        for (JmsTriggerConfiguration element : cnf.getJmsTriggerConfigurations()) {
            element.accept(visitor);
        }

        for (SchedulerConfiguration element : cnf.getSchedulerConfigurations()) {
            element.accept(visitor);
        }
        for (PortConfiguration element : cnf.getPortConfigurations()) {
            element.accept(visitor);
        }
    }

    protected void processOutput(Operation o) {
        ConfigurationVisited cnf = o.getVisited();
        ComponentConfigurationVisitor visitor = o.getVisitor();
        for (AdapterConnectionConfiguration element : cnf.getAdapterConnectionConfigurations()) {
            element.accept(visitor);
        }
        for (JmsAliasConfiguration element : cnf.getJmsAliasConfigurations()) {
            element.accept(visitor);
        }
    }
}
