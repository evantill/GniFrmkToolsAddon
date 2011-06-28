package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.api.visitor.configuration.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.component.ComponentType;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/06/11
 * Time: 10:01
 *
 * @author: e03229
 */
public interface Configuration<C extends Configuration<C>> extends Comparable<C> {
    ConfigurationId getId();

    ConfigurationInfo<?> getInfo();

    Set<ComponentConfiguration<?, ?, ?, ?>> getComponentConfigurations();

    Set<ComponentType<?, ?, ?, ?, ?>> getComponentConfigurationTypes();

    Set<ComponentConfiguration<?, ?, ?, ?>> getComponentConfigurationsByType(ComponentType<?, ?, ?, ?, ?> type);

    void accept(ConfigurationVisitor visitor);

}
