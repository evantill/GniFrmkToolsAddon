package com.gni.frmk.tools.addon.model.component.state;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:10
 *
 * @author: e03229
 */
public class ActivableState extends EnableState {
    public enum ActivableStatus {
        UNKNOWN {
            @Override
            public boolean isActive() {
                return false;
            }

            @Override
            public ActivableStatus invert() {
                return UNKNOWN;
            }
        }, ACTIVE {
            @Override
            public boolean isActive() {
                return true;
            }

            @Override
            public ActivableStatus invert() {
                return INACTIVE;
            }
        },
        INACTIVE {
            @Override
            public boolean isActive() {
                return false;
            }

            @Override
            public ActivableStatus invert() {
                return ACTIVE;
            }
        };

        public abstract boolean isActive();

        public boolean isNotActive() {
            return !isActive();
        }

        public abstract ActivableStatus invert();

        public static ActivableStatus fromStateString(String stateValue, String activeString, String inactiveString) {
            if (activeString.equals(stateValue)) {
                return ACTIVE;
            }
            if (inactiveString.equals(stateValue)) {
                return INACTIVE;
            } else {
                return UNKNOWN;
            }
        }

        public static ActivableStatus fromBooleanString(String active) {
            return fromBoolean(Boolean.parseBoolean(active));
        }

        public static ActivableStatus fromBoolean(boolean active) {
            return active ? ACTIVE : INACTIVE;
        }
    }

    private final ActivableStatus activable;

    public ActivableState(EnableStatus enabled, ActivableStatus activable) {
        super(enabled);
        this.activable = activable;
    }

    private ActivableState() {
        super();
        activable = ActivableStatus.UNKNOWN;
    }

    public ActivableStatus getActivable() {
        return activable;
    }
}
