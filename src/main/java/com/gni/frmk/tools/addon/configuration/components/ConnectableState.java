package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.components.Component.ComponentState;

import static com.gni.frmk.tools.addon.configuration.components.Component.ComponentState.ComponentStateStatus.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:47
 *
 * @author: e03229
 */
public class ConnectableState extends EnableState implements ComponentState {
    public enum ConnectableStatus {
        CONNECTED, DISCONNECTED
    }

    private final ConnectableStatus connected;

    public ConnectableState(EnableStatus enabled, ConnectableStatus connected) {
        super(enabled);
        this.connected = connected;
    }

    public ConnectableStatus getConnected() {
        return connected;
    }

    @Override
    public ComponentStateStatus getComponentStatus() {
        ComponentStateStatus enableStatus = super.getComponentStatus();
        switch (connected) {
            case CONNECTED:
                return enableStatus.composeWith(ON);
            case DISCONNECTED:
                return enableStatus.composeWith(OFF);
            default:
                return enableStatus.composeWith(UNKNOWN);
        }
    }

}
