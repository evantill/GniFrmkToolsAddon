package com.gni.frmk.tools.addon.tdd.api.command;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 18:31
 *
 * @author: e03229
 */
public interface Command {
    void execute(CommandContext context) throws CommandException;
}
