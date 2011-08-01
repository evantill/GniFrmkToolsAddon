package com.gni.frmk.tools.addon.tdd.impl.component.integrationserver;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentVisitor;
import com.gni.frmk.tools.addon.tdd.util.UnimplementedMethodException;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 13:55
 *
 * @author: e03229
 */
public class IntegrationServer implements Component<IntegrationServer, IntegrationServerState> {

    private Iterable<Component<?, ?>> components;

    @Override
    public void open(IntegrationServerState state) {
        restoreState(state);
        open();
    }

    @Override
    public void restoreState(IntegrationServerState state) {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public void open() {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public void close(IntegrationServerState state) {
        restoreState(state);
        close();
    }

    @Override
    public void close() {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public IntegrationServerState getState() {
        return saveState();
    }

    @Override
    public IntegrationServerState saveState() {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        for (Component<?, ?> component : components) {
            component.accept(visitor);
        }
        visitor.visitComponent(this);
    }

    @Override
    public int compareTo(IntegrationServer o) {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

}
