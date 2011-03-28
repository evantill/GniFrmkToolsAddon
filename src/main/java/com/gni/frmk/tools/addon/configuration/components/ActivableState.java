package com.gni.frmk.tools.addon.configuration.components;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static com.gni.frmk.tools.addon.configuration.components.ComponentState.ComponentStateStatus.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:10
 *
 * @author: e03229
 */
@XmlRootElement
public class ActivableState extends EnableState implements ComponentState {
    public enum ActivableStatus {
        ACTIVE {
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

        public abstract ActivableStatus invert();

        public static ActivableStatus fromStateString(String stateValue, String activeString, String inactiveString) {
            if (activeString.equals(stateValue)) {
                return ACTIVE;
            }
            if (inactiveString.equals(stateValue)) {
                return INACTIVE;
            }
            throw new IllegalStateException(String.format("invalid activable state %s", stateValue));
        }

        public static ActivableStatus fromBooleanString(String active) {
            return fromBoolean(Boolean.parseBoolean(active));
        }

        public static ActivableStatus fromBoolean(boolean active) {
            return active ? ACTIVE : INACTIVE;
        }
    }

    @XmlElement
    private final ActivableStatus activable;

    public ActivableState(EnableStatus enabled, ActivableStatus activable) {
        super(enabled);
        this.activable = activable;
    }

    private ActivableState(){
        super();
        activable=null;
    }

    public ActivableStatus getActivable() {
        return activable;
    }

    @Override
    public ComponentStateStatus getComponentStatus() {
        ComponentStateStatus enableStatus = super.getComponentStatus();
        switch (activable) {
            case ACTIVE:
                return enableStatus.composeWith(ON);
            case INACTIVE:
                return enableStatus.composeWith(OFF);
            default:
                return enableStatus.composeWith(UNKNOWN);
        }
    }

    @Override
    public ComponentStateType getType() {
        return ComponentStateType.ACTIAVABLE_STATE;
    }
}
