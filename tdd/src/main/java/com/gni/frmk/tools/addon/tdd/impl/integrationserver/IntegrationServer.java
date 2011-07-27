package com.gni.frmk.tools.addon.tdd.impl.integrationserver;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.util.UnimplementedMethodException;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 13:55
 *
 * @author: e03229
 */
public class IntegrationServer implements Component<IntegrationServer, IntegrationServerState> {

    @Override
    public void open() {
        open(getState());
    }

    @Override
    public void open(IntegrationServerState state) {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public IntegrationServerState getState() {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public void close() {
        close(getState());
    }

    @Override
    public void close(IntegrationServerState state) {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public int compareTo(IntegrationServer o) {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public IntegrationServerType getType() {
        //TODO implement method
        throw new UnimplementedMethodException();
    }

    @Override
    public IntegrationServerId getId() {
        //TODO implement method
        throw new UnimplementedMethodException();
    }
}
