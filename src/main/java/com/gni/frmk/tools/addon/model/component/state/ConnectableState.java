package com.gni.frmk.tools.addon.model.component.state;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:47
 *
 * @author: e03229
 */
public class ConnectableState extends EnableState {
    public static enum ConnectableStatus {
        UNKNOWN, CONNECTED, DISCONNECTED;

        public static ConnectableStatus fromBoolean(boolean connected) {
            return connected ? CONNECTED : DISCONNECTED;
        }
    }

    private ConnectableStatus connected;

    public ConnectableState() {
    }

    public ConnectableState(EnableStatus enabled, ConnectableStatus connected) {
        super(enabled);
        this.connected = connected;
    }

    public ConnectableStatus getConnected() {
        return connected;
    }

    public void setConnected(ConnectableStatus connected) {
        this.connected = connected;
    }
}
