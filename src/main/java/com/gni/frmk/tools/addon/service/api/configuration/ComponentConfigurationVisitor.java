package com.gni.frmk.tools.addon.service.api.configuration;

import com.gni.frmk.tools.addon.model.component.AbstractComponent;
import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.service.api.Visitor;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 20:15
 *
 * @author: e03229
 */
public interface ComponentConfigurationVisitor extends Visitor<ComponentConfigurationVisitor, ComponentConfigurationVisited> {

    <C extends AbstractComponent<?, S>, S extends AbstractComponentState>
    void visit(ComponentConfiguration<C, S> visited);

}
