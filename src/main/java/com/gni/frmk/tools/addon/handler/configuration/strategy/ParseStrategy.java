package com.gni.frmk.tools.addon.handler.configuration.strategy;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisited;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor.VisitorException;
import com.gni.frmk.tools.addon.api.configuration.ConfigurationProcessingStrategy;
import com.gni.frmk.tools.addon.model.configuration.component.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 20:33
 *
 * @author: e03229
 */
public class ParseStrategy implements ConfigurationProcessingStrategy {

    @Override
    public ParseStrategy execute(Operation o) {
        try {
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
            for (AdapterConnectionConfiguration element : cnf.getAdapterConnectionConfigurations()) {
                element.accept(visitor);
            }
            for (JmsAliasConfiguration element : cnf.getJmsAliasConfigurations()) {
                element.accept(visitor);
            }
            return this;
        } catch (VisitorException e) {
            throw new IllegalStateException(e);
        }
    }

}