package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.api.command.CommandContext;
import com.gni.frmk.tools.addon.tdd.api.command.CommandException;
import com.gni.frmk.tools.addon.tdd.api.command.CommandListener;
import com.gni.frmk.tools.addon.tdd.api.command.UndoableCommand;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 19:45
 *
 * @author: e03229
 */
public class UndoableCommandTracer
        extends CommandTracer
        implements UndoableCommand {

    private final UndoableCommand delegate;


    public UndoableCommandTracer(UndoableCommand delegate, CommandListener listener) {
        super(delegate, listener);
        this.delegate = delegate;
    }

    @Override
    public void rollback(CommandContext context) throws CommandException {
        try {
            delegate.rollback(context);
            getListener().rollbackDone(delegate);
        } catch (CommandException cause) {
            getListener().rollbackFailed(delegate, cause);
            throw cause;
        }
    }
}
