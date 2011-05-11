package com.gni.frmk.tools.addon.model.component.state;

import com.gni.frmk.tools.addon.model.component.BaseComponent.AbstractState;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 11:55
 *
 * @author: e03229
 */
public class EnableState extends AbstractState {
    public static enum EnableStatus {
        UNKNOWN {
            @Override
            public boolean isEnabled() {
                return false;
            }
        }, ENABLED {
            @Override
            public boolean isEnabled
                    () {
                return true;
            }
        }, DISABLED {
            @Override
            public boolean isEnabled
                    () {
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

    private EnableStatus enabled = EnableStatus.UNKNOWN;

    public EnableState() {
        super(false);
    }

    public EnableState(EnableStatus enabled) {
        super(enabled != EnableStatus.UNKNOWN);
        this.enabled = enabled;
    }

    public EnableStatus getEnabled() {
        return enabled;
    }

    public void setEnabled(EnableStatus enabled) {
        this.enabled = enabled;
    }
}
