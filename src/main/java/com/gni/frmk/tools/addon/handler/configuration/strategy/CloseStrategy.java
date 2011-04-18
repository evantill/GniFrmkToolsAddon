package com.gni.frmk.tools.addon.handler.configuration.strategy;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisited;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.api.configuration.ConfigurationProcessingStrategy;
import com.gni.frmk.tools.addon.model.configuration.component.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 20:32
 *
 * @author: e03229
 */
public class CloseStrategy implements ConfigurationProcessingStrategy {

    @Override
    public ConfigurationProcessingStrategy execute(Operation o) {
        processInput(o);
        waitServicesEnd(o);
        processOutput(o);
        return this;
    }

    protected void processInput(Operation o) {
        ConfigurationVisited cnf = o.getVisited();
        ConfigurationVisitor visitor = o.getVisitor();
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
        ConfigurationVisitor visitor = o.getVisitor();
        for (AdapterConnectionConfiguration element : cnf.getAdapterConnectionConfigurations()) {
            element.accept(visitor);
        }
        for (JmsAliasConfiguration element : cnf.getJmsAliasConfigurations()) {
            element.accept(visitor);
        }
        for (IntegrationServerPackageConfiguration element : cnf.getIntegrationServerPackageConfigurations()) {
            element.accept(visitor);
        }
    }

    protected void waitServicesEnd(Operation o) {
        //TODO implementer
        throw new UnsupportedOperationException("waitServicesEnd not implemented");
    }

}
