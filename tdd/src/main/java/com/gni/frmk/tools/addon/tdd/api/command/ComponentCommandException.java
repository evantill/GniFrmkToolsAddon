package com.gni.frmk.tools.addon.tdd.api.command;

import com.gni.frmk.tools.addon.tdd.api.Component;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/08/11
 * Time: 15:09
 *
 * @author: e03229
 */
public class ComponentCommandException extends CommandException {
    private final Component component;

    public ComponentCommandException(String message, Command command, Component component) {
        super(message, command);
        this.component = component;
    }

    public ComponentCommandException(ComponentCommandException exception) {
        super(exception);
        this.component = exception.getComponent();
    }

    public Component getComponent() {
        return component;
    }

    @Override
    public String getMessage() {
        return String.format("%s on %s", super.getMessage(), component);
    }
}
