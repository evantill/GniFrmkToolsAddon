package com.gni.frmk.tools.addon.tdd.tdd3.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/08/11
 * Time: 16:52
 *
 * @author: e03229
 */
public enum ComponentState {
    UNKNOWN, OPENED(true, true, false), CLOSED(true, false, true), TRANSIENT(true, false, false);

    private final boolean defined;
    private final boolean opened;
    private final boolean closed;

    ComponentState() {
        this(false, false, false);
    }

    ComponentState(boolean defined, boolean opened, boolean closed) {
        this.defined = defined;
        this.opened = opened;
        this.closed = closed;
    }

    public boolean isDefined() {
        return defined;
    }

    public boolean isOpened() {
        return opened;
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean isUndefined() {
        return !defined;
    }
}
