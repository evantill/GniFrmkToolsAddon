package com.gni.frmk.tools.addon.service.configuration.strategy;

import com.gni.frmk.tools.addon.model.configuration.component.*;
import com.gni.frmk.tools.addon.service.api.configuration.ComponentConfigurationVisitor;
import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationProcessingStrategy;
import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationVisited;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 20:09
 *
 * @author: e03229
 */
public class OpenStrategy implements ConfigurationProcessingStrategy {

    @Override
    public ConfigurationProcessingStrategy execute(Operation o) {
        processOutput(o);
        processInput(o);
        return this;
    }

    protected void processInput(Operation o) {
        ConfigurationVisited cnf = o.getVisited();
        ComponentConfigurationVisitor visitor = o.getVisitor();
        for (PortConfiguration element : cnf.getPortConfigurations()) {
            element.accept(visitor);
        }
        for (SchedulerConfiguration element : cnf.getSchedulerConfigurations()) {
            element.accept(visitor);
        }
        for (JmsTriggerConfiguration element : cnf.getJmsTriggerConfigurations()) {
            element.accept(visitor);
        }
        for (NativeTriggerConfiguration element : cnf.getNativeTriggerConfigurations()) {
            element.accept(visitor);
        }
        for (AdapterNotificationConfiguration element : cnf.getAdapterNotificationConfigurations()) {
            element.accept(visitor);
        }
        for (AdapterListenerConfiguration element : cnf.getAdapterListenerConfigurations()) {
            element.accept(visitor);
        }
    }

    protected void processOutput(Operation o) {
        ConfigurationVisited cnf = o.getVisited();
        ComponentConfigurationVisitor visitor = o.getVisitor();
        for (IntegrationServerPackageConfiguration element : cnf.getIntegrationServerPackageConfigurations()) {
            element.accept(visitor);
        }
        for (JmsAliasConfiguration element : cnf.getJmsAliasConfigurations()) {
            element.accept(visitor);
        }
        for (AdapterConnectionConfiguration element : cnf.getAdapterConnectionConfigurations()) {
            element.accept(visitor);
        }
    }

}
