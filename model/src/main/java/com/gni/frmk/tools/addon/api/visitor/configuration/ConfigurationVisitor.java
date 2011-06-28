package com.gni.frmk.tools.addon.api.visitor.configuration;

import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.model.configuration.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/05/11
 * Time: 08:38
 *
 * @author: e03229
 */
public interface ConfigurationVisitor {
    void visitConfiguration(Configuration<?> visited);

    void visitComponentConfiguration(ComponentConfiguration<?, ?, ?, ?> visited);
}
