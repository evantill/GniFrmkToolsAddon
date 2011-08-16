package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.api.command.Command;
import com.gni.frmk.tools.addon.tdd.api.command.CommandContext;
import com.gni.frmk.tools.addon.tdd.api.command.CommandException;
import com.gni.frmk.tools.addon.tdd.api.command.MultiCommandException;
import com.gni.frmk.tools.addon.tdd.api.command.UndoableCommand;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 18:35
 *
 * @author: e03229
 */
public class RollbackMacroCommand extends MacroCommand {
    private final List<UndoableCommand> done = Lists.newArrayList();

    @Override
    public void execute(CommandContext context) throws CommandException {
        done.clear();
        super.execute(context);
    }

    @Override
    protected void doExecute(CommandContext context, Command command) throws CommandException {
        try {
            if (canUndo(command)) {
                doUndoableCommand(context, command);
            } else {
                super.doExecute(context, command);
            }
        } catch (CommandException cause) {
            doRollbackOnError(context);
            throw cause;
        }
    }

    private boolean canUndo(Command command) {
        return UndoableCommand.class.isInstance(command);
    }

    private void doUndoableCommand(CommandContext context, Command command) throws CommandException {
        super.doExecute(context, command);
        done.add(UndoableCommand.class.cast(command));
    }

    private void doRollbackOnError(CommandContext context) throws CommandException {
        List<CommandException> exceptions = Lists.newArrayList();
        for (UndoableCommand command : done) {
            try {
                command.rollback(context);
            } catch (CommandException cause) {
                exceptions.add(cause);
            }
        }
        MultiCommandException.throwFirstExceptionIfNeeded(exceptions);
    }

}
