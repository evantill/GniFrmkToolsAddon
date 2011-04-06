package com.gni.frmk.tools.addon.model.component.state;

import com.gni.frmk.tools.addon.model.api.ComponentStateType;
import com.gni.frmk.tools.addon.model.api.ComponentState;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static com.gni.frmk.tools.addon.model.api.ComponentState.ComponentStateStatus.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:47
 *
 * @author: e03229
 */
@XmlRootElement
public class ConnectableState extends EnableState implements ComponentState {
    public enum ConnectableStatus {
        CONNECTED, DISCONNECTED;

        public static ConnectableStatus fromBoolean(boolean connected) {
            return connected?CONNECTED:DISCONNECTED;
        }
    }

    @XmlElement
    private final ConnectableStatus connected;

    public ConnectableState(EnableStatus enabled, ConnectableStatus connected) {
        super(enabled);
        this.connected = connected;
    }

    protected  ConnectableState(){
        super();
        connected=null;
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

    @Override
    public ComponentStateType getType() {
        return ComponentStateType.CONNECTABLE_STATE;
    }
}
