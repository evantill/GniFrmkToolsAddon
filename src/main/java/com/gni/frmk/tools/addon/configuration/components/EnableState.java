package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.components.Component.ComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 11:55
 *
 * @author: e03229
 */
public class EnableState implements ComponentState {
    public enum EnableStatus {
        ENABLED, DISABLED
    }

    private final EnableStatus enabled;

    public EnableState(EnableStatus enabled) {
        this.enabled = enabled;
    }

}
