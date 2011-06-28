package com.gni.frmk.tools.addon.api.visitor.configuration.strategy;

import com.gni.frmk.tools.addon.api.visitor.configuration.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.configuration.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/06/11
 * Time: 11:01
 *
 * @author: e03229
 */
public interface ConfigurationVisitorStrategy<V extends ConfigurationVisitor> {
    <T extends V> void visitConfiguration(T visitor, Configuration<?> visited);
}
