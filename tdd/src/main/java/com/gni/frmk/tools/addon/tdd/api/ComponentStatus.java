package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 08/08/11
 * Time: 17:25
 *
 * @author: e03229
 */
public enum ComponentStatus {
    UNKNOWN, OPENED(true, true), CLOSED(true, false), TRANSIENT(false, false);

    private final boolean defined;
    private final boolean opened;
    private final boolean closed;

    private ComponentStatus(boolean opened, boolean closed) {
        defined = true;
        this.opened = opened;
        this.closed = closed;
    }

    private ComponentStatus() {
        defined = false;
        opened = false;
        closed = false;
    }

    public boolean isDefined() {
        return defined;
    }

    public boolean isUnknown() {
        return !defined;
    }

    public boolean isOpened() {
        return defined && opened;
    }

    public boolean isClosed() {
        return defined && closed;
    }

    public boolean isTransient() {
        return defined && !opened && !closed;
    }
}
