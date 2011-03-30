package com.gni.frmk.tools.addon.configuration.component;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 20:32
 *
 * @author: e03229
 */
public interface ComponentState {
    public static enum ComponentStateStatus {
        UNKNOWN, ON, OFF, CHANGING;

        public ComponentStateStatus composeWith(final ComponentStateStatus other) {
            if (this == other) {
                return this;
            }
            if (this == UNKNOWN || other == UNKNOWN) {
                return UNKNOWN;
            }
            if (this == CHANGING || other == CHANGING) {
                return CHANGING;
            }
            return OFF;
        }
    }

    ComponentStateStatus getComponentStatus();

    ComponentStateType getType();
}
