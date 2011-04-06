package com.gni.frmk.tools.addon.service.api.configuration;

import com.gni.frmk.tools.addon.service.api.Strategy;
import com.gni.frmk.tools.addon.service.api.component.ComponentVisitor;
import com.gni.frmk.tools.addon.service.api.configuration.ConfigurationProcessingStrategy.Operation;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 19:01
 *
 * @author: e03229
 */
public interface ConfigurationProcessingStrategy extends Strategy<ConfigurationProcessingStrategy, Operation> {

    public static interface Operation {
        ComponentConfigurationVisitor getVisitor();

        ConfigurationVisited getVisited();
    }

    ConfigurationProcessingStrategy execute(Operation o);
}
