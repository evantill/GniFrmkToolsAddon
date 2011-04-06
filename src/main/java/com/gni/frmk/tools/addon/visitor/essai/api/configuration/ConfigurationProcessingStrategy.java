package com.gni.frmk.tools.addon.visitor.essai.api.configuration;

import com.gni.frmk.tools.addon.visitor.essai.api.Strategy;
import com.gni.frmk.tools.addon.visitor.essai.api.component.ComponentVisitor;
import com.gni.frmk.tools.addon.visitor.essai.api.configuration.ConfigurationProcessingStrategy.Operation;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 19:01
 *
 * @author: e03229
 */
public interface ConfigurationProcessingStrategy extends Strategy<ConfigurationProcessingStrategy, Operation> {

    public static interface Operation {
        ComponentVisitor getVisitor();

        ConfigurationVisited getVisited();
    }

    ConfigurationProcessingStrategy execute(Operation o);
}
