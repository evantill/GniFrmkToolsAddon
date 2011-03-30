package com.gni.frmk.tools.addon.configuration.visitor;

import com.gni.frmk.tools.addon.configuration.component.Component;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 19:43
 *
 * @author: e03229
 */
public class ComponentVisitorException extends Exception {
    private final Component component;

    public ComponentVisitorException(Component component, Throwable cause) {
        super(cause);
        this.component = component;
    }

    public ComponentVisitorException(Component component, String message) {
        super(message);
        this.component = component;
    }

    public Component getComponent() {
        return component;
    }
}
