package com.gni.frmk.tools.addon.api.custom.visitor;

import com.gni.frmk.tools.addon.api.visitor.Visitor;
import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.model.configuration.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 17:39
 *
 * @author: e03229
 */
public interface ConfigurationVisitor
        extends Visitor<ConfigurationVisitor, ConfigurationVisited> {

    void visitComponent(Component<?,?,?> visited);

    void visitConfiguration(Configuration configuration);

    void visitComponentConfiguration(ComponentConfiguration visited);
}
