package com.gni.frmk.tools.addon.tdd.api.command;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 18:32
 *
 * @author: e03229
 */
public class CommandException extends Exception {
    private final Command command;


    public CommandException(String message, Command command) {
        super(message);
        this.command = command;
    }

    public CommandException(CommandException exception) {
        super(exception);
        this.command = exception.getCommand();
    }

    public CommandException(Exception cause, Command command) {
        super(cause);
        this.command = command;
    }

    @Override
    public String getMessage() {
        return String.format("command %s failed : %s", command, super.getMessage());
    }

    public Command getCommand() {
        return command;
    }

}
