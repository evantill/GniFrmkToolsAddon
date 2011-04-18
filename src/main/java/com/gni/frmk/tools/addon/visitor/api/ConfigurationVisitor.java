package com.gni.frmk.tools.addon.visitor.api;

import com.gni.frmk.tools.addon.model.component.AbstractComponent;
import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.model.configuration.component.ComponentConfiguration;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public interface ConfigurationVisitor extends com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor {
    <C extends AbstractComponent<?, S>, S extends AbstractComponentState>
    void visitComponentConfiguration(ComponentConfiguration<C, S> visited);

    void clear();


}
