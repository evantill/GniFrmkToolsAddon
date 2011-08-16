package com.gni.frmk.tools.addon.tdd.api;

/**
 * Created by IntelliJ IDEA.
 * Date: 08/08/11
 * Time: 17:25
 *
 * @author: e03229
 */
public enum ComponentStatus {
    UNKNOWN, OPENED, CLOSED, TRANSIENT;

    public boolean isOpened() {
        switch (this) {
            case OPENED:
                return true;
            default:
                return false;
        }
    }

    public boolean isClosed() {
        switch (this) {
            case CLOSED:
                return true;
            default:
                return false;
        }
    }
}
