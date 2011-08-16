package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.api.command.Command;
import com.gni.frmk.tools.addon.tdd.api.command.CommandContext;
import com.gni.frmk.tools.addon.tdd.api.command.CommandException;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 18:31
 *
 * @author: e03229
 */
public class MacroCommand implements Command {
    private final List<Command> commands = Lists.newArrayList();

    public void add(Command command) {
        commands.add(command);
    }

    @Override
    public void execute(CommandContext context) throws CommandException {
        for (Command command : commands) {
            doExecute(context, command);
        }
    }

    protected void doExecute(CommandContext context, Command command) throws CommandException {
        command.execute(context);
    }
}
