package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.api.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 19:01
 *
 * @author: e03229
 */
public class CheckIsOpenedCommand implements Command {
    private final ComponentType type;
    private final ComponentId id;

    public CheckIsOpenedCommand(Component component) {
        this(component.getType(), component.getId());
    }

    public CheckIsOpenedCommand(ComponentType type, ComponentId id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public void execute(CommandContext context) throws CommandException {
        Component component = context.findComponent(type, id);
        if (!component.getStatus().isOpened()) {
            throw new CommandException("failed to open component", this, component);
        }
    }
}
