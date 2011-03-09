package com.gni.frmk.tools.addon.data3.components.integrationServer;

import com.gni.frmk.tools.addon.data3.components.ComponentState;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 07/03/11
 * Time: 19:05
 * To change this template use File | Settings | File Templates.
 */
public class EnableComponentState  implements ComponentState {

    public EnableComponentState(EnableStatus enabled) {this.enabled = enabled;}

    public enum EnableStatus {
        ENABLED, DISABLED;
    }

    private final EnableStatus enabled;

    public EnableStatus getEnabled() {
        return enabled;
    }
}
