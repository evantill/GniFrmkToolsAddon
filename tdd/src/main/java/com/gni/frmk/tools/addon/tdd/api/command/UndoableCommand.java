package com.gni.frmk.tools.addon.tdd.api.command;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 18:32
 *
 * @author: e03229
 */
public interface UndoableCommand extends Command {
    void rollback(CommandContext context) throws CommandException;
}
