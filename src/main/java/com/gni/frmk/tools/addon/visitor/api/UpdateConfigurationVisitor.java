package com.gni.frmk.tools.addon.visitor.api;

import com.gni.frmk.tools.addon.model.configuration.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 19:35
 *
 * @author: e03229
 */
public interface UpdateConfigurationVisitor extends ConfigurationVisitor {
    Configuration getUpdatedConfiguration();
}
