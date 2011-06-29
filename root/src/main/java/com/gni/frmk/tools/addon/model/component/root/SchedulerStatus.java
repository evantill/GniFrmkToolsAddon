package com.gni.frmk.tools.addon.model.component.root;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 18:06
 *
 * @author: e03229
 */
public enum SchedulerStatus {
    UNKNOWN, UNEXPIRED, EXPIRED;

    public static SchedulerStatus fromBoolean(boolean expired) {
        return expired ? EXPIRED : UNEXPIRED;
    }
}
