package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.configuration.Configuration;
import com.gni.frmk.tools.addon.configuration.components.*;
import com.gni.frmk.tools.addon.configuration.components.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.configuration.visitors.ComponentVisitor;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public interface ConfigurationVisitor extends ComponentVisitor {
    
    <C extends AbstractComponent<?,S>,S extends AbstractComponentState> void visitComponentConfiguration(ComponentConfiguration<C,S> visited);
    void clear();
}
