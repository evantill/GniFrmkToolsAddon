package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentId;
import com.gni.frmk.tools.addon.tdd.api.ComponentState;
import com.gni.frmk.tools.addon.tdd.api.ComponentType;
import com.gni.frmk.tools.addon.tdd.api.command.CommandContext;
import com.gni.frmk.tools.addon.tdd.api.command.CommandException;
import com.gni.frmk.tools.addon.tdd.api.command.UndoableCommand;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 19:01
 *
 * @author: e03229
 */
public class OpenCommandIfNeeded<S extends ComponentState> implements UndoableCommand {
    private final ComponentType type;
    private final ComponentId id;

    private S savedState;

    private OpenCommandIfNeeded(Component<S> component) {
        this(component.getType(), component.getId());
    }

    private OpenCommandIfNeeded(ComponentType type, ComponentId id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public void execute(CommandContext context) throws CommandException {
        savedState = null;
        Component<S> component = context.findComponent(type, id);
        if (!component.getStatus().isOpened()) {
            savedState = component.saveState();
            component.open();
        }
    }

    @Override
    public void rollback(CommandContext context) throws CommandException {
        Component<S> component = context.findComponent(type, id);
        if (savedState != null) {
            component.restoreState(savedState);
            component.close();
        }
    }

    public static final <S extends ComponentState> OpenCommandIfNeeded<S> createCommand(Component<S> component) {
        return new OpenCommandIfNeeded<S>(component);
    }
}
