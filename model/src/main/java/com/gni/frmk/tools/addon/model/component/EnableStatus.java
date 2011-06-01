package com.gni.frmk.tools.addon.model.component;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 13:51
 *
 * @author: e03229
 */
public enum EnableStatus {
    UNKNOWN(false), ENABLED(true), DISABLED(false);

    private final boolean enabled;

    EnableStatus(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public static EnableStatus fromBooleanString(String enabled) {
        return fromBoolean(Boolean.parseBoolean(enabled));
    }

    public static EnableStatus fromBoolean(boolean enabled) {
        return enabled ? ENABLED : DISABLED;
    }

    public static boolean isEnabling(EnableStatus from, EnableStatus to) {
        return from == DISABLED && to == ENABLED;
    }

    public static boolean isDisabling(EnableStatus from, EnableStatus to) {
        return from == ENABLED && to == DISABLED;
    }
}
