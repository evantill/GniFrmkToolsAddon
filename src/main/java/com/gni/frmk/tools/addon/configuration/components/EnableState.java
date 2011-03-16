package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.components.Component.ComponentState;

import static com.gni.frmk.tools.addon.configuration.components.Component.ComponentState.ComponentStateStatus.OFF;
import static com.gni.frmk.tools.addon.configuration.components.Component.ComponentState.ComponentStateStatus.ON;
import static com.gni.frmk.tools.addon.configuration.components.Component.ComponentState.ComponentStateStatus.UNKNOWN;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 11:55
 *
 * @author: e03229
 */
public class EnableState implements ComponentState {
    public enum EnableStatus {
        ENABLED, DISABLED;

        public static EnableStatus fromBooleanString(String enabled) {
            return Boolean.parseBoolean(enabled)?ENABLED:DISABLED;
        }

        public static EnableStatus fromValueString(String enabled) {
            return valueOf(enabled.toUpperCase());
        }
    }

    private final EnableStatus enabled;

    public EnableState(EnableStatus enabled) {
        this.enabled = enabled;
    }

    public EnableStatus getEnabled() {
        return enabled;
    }

    @Override
    public ComponentStateStatus getComponentStatus() {
        switch (enabled) {
            case ENABLED:
                return ON;
            case DISABLED:
                return OFF;
            default:
                return UNKNOWN;
        }
    }
}
