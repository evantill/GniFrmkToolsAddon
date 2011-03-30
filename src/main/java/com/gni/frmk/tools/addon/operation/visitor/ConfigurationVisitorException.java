package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.configuration.component.Component;
import com.gni.frmk.tools.addon.configuration.visitor.ComponentVisitorException;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/11/10
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationVisitorException extends ComponentVisitorException {

    //TODO ajouter la configuration

    public ConfigurationVisitorException(Component component, Throwable caught) {
        super(component, caught);
    }

    public ConfigurationVisitorException(Component component, String message) {
        super(component, message);
    }

}
