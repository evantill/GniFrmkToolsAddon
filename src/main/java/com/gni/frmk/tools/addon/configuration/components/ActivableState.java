package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.components.Component.ComponentState;

import static com.gni.frmk.tools.addon.configuration.components.Component.ComponentState.ComponentStateStatus.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:10
 *
 * @author: e03229
 */
public class ActivableState extends EnableState implements ComponentState {
    public enum ActivableStatus {
        ACTIVE, INACTIVE
    }

    private final ActivableStatus activable;

    public ActivableState(EnableStatus enabled, ActivableStatus activable) {
        super(enabled);
        this.activable = activable;
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

}
