package com.gni.frmk.tools.addon.configuration.component;

import com.gni.frmk.tools.addon.configuration.component.AbstractComponent.AbstractComponentState;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static com.gni.frmk.tools.addon.configuration.component.ComponentState.ComponentStateStatus.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 11:55
 *
 * @author: e03229
 */
@XmlRootElement
public class EnableState extends AbstractComponentState implements ComponentState {
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

    @XmlElement
    private final EnableStatus enabled;

    public EnableState(EnableStatus enabled) {
        this.enabled = enabled;
    }

    protected EnableState(){
        enabled=null;
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

    @Override
    public ComponentStateType getType() {
        return ComponentStateType.ENABLE_STATE;
    }
}
