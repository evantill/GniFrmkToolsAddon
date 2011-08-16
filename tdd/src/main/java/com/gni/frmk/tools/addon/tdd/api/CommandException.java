package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 18:32
 *
 * @author: e03229
 */
public class CommandException extends Exception {
    private final Command command;
    private final Component component;

    public CommandException(String message, Command command, Component component) {
        super(message);
        this.command = command;
        this.component = component;
    }

    public CommandException(CommandException exception) {
        super(exception);
        this.command = exception.getCommand();
        this.component = exception.getComponent();
    }

    @Override
    public String getMessage() {
        return String.format("command %s failed : %s on %s", command, super.getMessage(), component);
    }

    public Command getCommand() {
        return command;
    }

    public Component getComponent() {
        return component;
    }
}
