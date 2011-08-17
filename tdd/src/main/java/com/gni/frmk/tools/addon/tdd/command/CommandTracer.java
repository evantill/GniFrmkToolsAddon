package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.api.command.Command;
import com.gni.frmk.tools.addon.tdd.api.command.CommandContext;
import com.gni.frmk.tools.addon.tdd.api.command.CommandException;
import com.gni.frmk.tools.addon.tdd.api.command.CommandListener;
import com.gni.frmk.tools.addon.tdd.api.command.UndoableCommand;

import static com.gni.frmk.tools.addon.tdd.command.CommandDecoratorFactory.createDecoratorFor;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 19:41
 *
 * @author: e03229
 */
public class CommandTracer
        extends CommandDecorator
        implements Command, UndoableCommand {
    private final CommandListener listener;

    private CommandTracer(Command decorated, CommandListener listener, Class... interfaces) {
        super(decorated, interfaces);
        this.listener = listener;
    }

    @Override
    public void execute(CommandContext context) throws CommandException {
        try {
            getDecorated().execute(context);
            listener.commandDone(getDecorated());
        } catch (CommandException cause) {
            listener.commandFailed(getDecorated(), cause);
            throw cause;
        } catch (RuntimeException cause) {
            listener.commandFailed(getDecorated(), cause);
            throw cause;
        }
    }

    @Override
    public void rollback(CommandContext context) throws CommandException {
        try {
            getDecoratedAs(UndoableCommand.class).rollback(context);
            listener.rollbackDone(getDecorated());
        } catch (CommandException cause) {
            listener.rollbackFailed(getDecorated(), cause);
            throw cause;
        } catch (RuntimeException cause) {
            listener.rollbackFailed(getDecorated(), cause);
            throw cause;
        }
    }

    protected CommandListener getListener() {
        return listener;
    }

    public static <C extends Command> C decorate(C decorated, CommandListener listener) {
        CommandTracer tracer = new CommandTracer(decorated, listener, Command.class, UndoableCommand.class);
        return (C) createDecoratorFor(decorated, tracer);
    }
}
