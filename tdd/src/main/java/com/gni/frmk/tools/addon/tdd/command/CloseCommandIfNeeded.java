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
public class CloseCommandIfNeeded<S extends ComponentState> implements UndoableCommand {
    private final ComponentType type;
    private final ComponentId id;

    private S savedState;

    private CloseCommandIfNeeded(Component<S> component) {
        this(component.getType(), component.getId());
    }

    private CloseCommandIfNeeded(ComponentType type, ComponentId id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public void execute(CommandContext context) throws CommandException {
        savedState = null;
        Component<S> component = context.findComponent(type, id);
        if (!component.getStatus().isClosed()) {
            savedState = component.saveState();
            component.close();
        }
    }

    @Override
    public void rollback(CommandContext context) throws CommandException {
        Component<S> component = context.findComponent(type, id);
        if (savedState != null) {
            component.restoreState(savedState);
            component.open();
        }
    }

    public static final <S extends ComponentState> CloseCommandIfNeeded<S> createCommand(Component<S> component) {
        return new CloseCommandIfNeeded<S>(component);
    }
}
