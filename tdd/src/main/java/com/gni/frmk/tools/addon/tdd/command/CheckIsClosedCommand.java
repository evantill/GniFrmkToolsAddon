package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.api.*;
import com.gni.frmk.tools.addon.tdd.api.command.Command;
import com.gni.frmk.tools.addon.tdd.api.command.CommandContext;
import com.gni.frmk.tools.addon.tdd.api.command.CommandException;
import com.gni.frmk.tools.addon.tdd.api.command.ComponentCommandException;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 19:01
 *
 * @author: e03229
 */
public class CheckIsClosedCommand implements Command {
    private final ComponentType type;
    private final ComponentId id;

    public CheckIsClosedCommand(Component component) {
        this(component.getType(), component.getId());
    }

    public CheckIsClosedCommand(ComponentType type, ComponentId id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public void execute(CommandContext context) throws CommandException {
        Component component = context.findComponent(type, id);
        if (!component.getStatus().isClosed()) {
            throw new ComponentCommandException("failed to close component", this, component);
        }
    }
}
