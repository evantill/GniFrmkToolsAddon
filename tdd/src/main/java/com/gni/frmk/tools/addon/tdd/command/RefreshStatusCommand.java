package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.api.*;
import com.gni.frmk.tools.addon.tdd.api.command.Command;
import com.gni.frmk.tools.addon.tdd.api.command.CommandContext;
import com.gni.frmk.tools.addon.tdd.api.command.CommandException;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 19:01
 *
 * @author: e03229
 */
public class RefreshStatusCommand implements Command {
    private final ComponentType type;
    private final  ComponentId id;

    public RefreshStatusCommand(Component component) {
        this(component.getType(),component.getId());
    }

    public RefreshStatusCommand(ComponentType type, ComponentId id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public void execute(CommandContext context) throws CommandException {
        Component component = context.findComponent(type, id);
        component.refreshStatus();
    }
}
