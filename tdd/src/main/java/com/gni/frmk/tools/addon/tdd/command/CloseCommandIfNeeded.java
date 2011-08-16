package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.api.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 19:01
 *
 * @author: e03229
 */
public class CloseCommandIfNeeded implements Command {
    private final ComponentType type;
    private final ComponentId id;

    public CloseCommandIfNeeded(Component component) {
        this(component.getType(), component.getId());
    }

    public CloseCommandIfNeeded(ComponentType type, ComponentId id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public void execute(CommandContext context) throws CommandException {
        Component component = context.findComponent(type, id);
        if (!component.getStatus().isClosed()) {
            component.close();
        }
    }
}
