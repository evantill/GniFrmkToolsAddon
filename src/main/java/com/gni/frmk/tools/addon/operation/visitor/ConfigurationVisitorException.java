package com.gni.frmk.tools.addon.operation.visitor;

import com.gni.frmk.tools.addon.data.ConfigurationElement;
import com.gni.frmk.tools.addon.data.component.Component;
import com.gni.frmk.tools.addon.invoke.ServiceException;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 03/11/10
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationVisitorException extends Exception {

    //TODO supprimer les references a ELEMENT apres migration datas
    private final ConfigurationElement element;
    private final Component component;

    public ConfigurationVisitorException(ConfigurationElement element, ServiceException e) {
        super(e);
        this.element = element;
        this.component = null;
    }

    public ConfigurationVisitorException(Component component, ServiceException e) {
        super(e);
        this.component = component;
        this.element = null;
    }

    public ConfigurationVisitorException(ConfigurationElement element, String message) {
        super(message);
        this.element = element;
        this.component = null;
    }

    public ConfigurationVisitorException(Component component, String message) {
        super(message);
        this.component = component;
        this.element = null;
    }

    public ConfigurationElement getElement() {
        return element;
    }

    public Component getComponent() {
        return component;
    }
}
