package com.gni.frmk.tools.addon.api.visitor.configuration.strategy;

import com.gni.frmk.tools.addon.api.visitor.configuration.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.model.configuration.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/06/11
 * Time: 11:07
 *
 * @author: e03229
 */
public class ConfigurationVisitorFullStrategy<V extends ConfigurationVisitor>
        implements ConfigurationVisitorStrategy<V> {

    @Override
    public <T extends V> void visitConfiguration(T visitor, Configuration<?> visited) {
        for (ComponentConfiguration<?, ?, ?, ?,?> componentConfiguration : visited.getComponentConfigurations()) {
            visitor.visitComponentConfiguration(componentConfiguration);
        }
    }
}
