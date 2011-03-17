package com.gni.frmk.tools.addon.configuration.components;

import static com.gni.frmk.tools.addon.configuration.components.ComponentState.ComponentStateStatus.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 11:55
 *
 * @author: e03229
 */
public class EnableState implements ComponentState {
    public enum EnableStatus {
        ENABLED {
            @Override
            public boolean isEnabled() {
                return true;
            }
        }, DISABLED {
            @Override
            public boolean isEnabled() {
                return false;
            }
        };

        public static EnableStatus fromBooleanString(String enabled) {
            return fromBoolean(Boolean.parseBoolean(enabled));
        }

        public static EnableStatus fromBoolean(boolean enabled) {
            return enabled ? ENABLED : DISABLED;
        }

        public abstract boolean isEnabled();
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
