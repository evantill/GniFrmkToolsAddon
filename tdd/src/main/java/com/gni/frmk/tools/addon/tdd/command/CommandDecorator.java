package com.gni.frmk.tools.addon.tdd.command;


import com.gni.frmk.tools.addon.tdd.api.command.Command;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/08/11
 * Time: 15:46
 *
 * @author: e03229
 */
public abstract class CommandDecorator {
    private final Command decorated;
    private final Class[] decoratedInterfaces;

    protected CommandDecorator(Command decorated, Class... decoratedInterfaces) {
        this.decorated = decorated;
        this.decoratedInterfaces = decoratedInterfaces;
    }

    protected Command getDecorated() {
        return decorated;
    }

    protected <T extends Command> T getDecoratedAs(Class<T> type) {
        return type.cast(decorated);
    }

    public Class[] getDecoratedInterfaces() {
        return decoratedInterfaces;
    }
}
