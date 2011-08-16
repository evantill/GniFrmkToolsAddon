package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.api.Command;
import com.gni.frmk.tools.addon.tdd.api.CommandContext;
import com.gni.frmk.tools.addon.tdd.api.CommandException;
import com.gni.frmk.tools.addon.tdd.api.CommandListener;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 19:41
 *
 * @author: e03229
 */
public class CommandTracer implements Command {
    private final Command delegate;
    private final CommandListener listener;

    public CommandTracer(Command delegate, CommandListener listener) {
        this.delegate = delegate;
        this.listener = listener;
    }

    @Override
    public void execute(CommandContext context) throws CommandException {
        try {
            delegate.execute(context);
            listener.commandDone(delegate);
        } catch (CommandException cause) {
            listener.commandFailed(delegate, cause);
            throw cause;
        }
    }

    protected CommandListener getListener() {
        return listener;
    }
}
