package com.gni.frmk.tools.addon.tdd.api.command;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 18:41
 *
 * @author: e03229
 */
public interface CommandListener {
    void commandDone(Command command);

    void commandFailed(Command command, CommandException e);

    void rollbackDone(Command command);

    void rollbackFailed(Command command, CommandException e);
}
