package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.components.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.configuration.components.Component.ComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:54
 *
 * @author: e03229
 */
public class TemporaryActivableState implements ComponentState {

    public enum TemporaryStatus {
        TEMPORARY {
            @Override
            public boolean isPermanent() {
                return false;
            }
        }, PERMANENT {
            @Override
            public boolean isPermanent() {
                return true;
            }
        };

        public abstract boolean isPermanent();
    }

    private final TemporaryStatus temporary;
    private final ActivableStatus activable;

    public TemporaryActivableState(TemporaryStatus temporary, ActivableStatus activable) {
        this.temporary = temporary;
        this.activable = activable;
    }

    public TemporaryStatus getTemporary() {
        return temporary;
    }

    public ActivableStatus getActivable() {
        return activable;
    }

    @Override
    public ComponentStateStatus getComponentStatus() {
        switch (activable) {
            case ACTIVE:
                return ComponentStateStatus.ON;
            case INACTIVE:
                return ComponentStateStatus.OFF;
            default:
                return ComponentStateStatus.UNKNOWN;
        }
    }

}
