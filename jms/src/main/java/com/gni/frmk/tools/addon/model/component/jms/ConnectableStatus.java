package com.gni.frmk.tools.addon.model.component.jms;

/**
* Created by IntelliJ IDEA.
* Date: 17/05/11
* Time: 13:51
*
* @author: e03229
*/
public enum ConnectableStatus {
    UNKNOWN, CONNECTED, DISCONNECTED;

    public static ConnectableStatus fromBoolean(boolean connected) {
        return connected ? CONNECTED : DISCONNECTED;
    }
}
