package com.gni.frmk.tools.addon.model.component.root;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 10:58
 *
 * @author: e03229
 */
public enum SuspendedStatus {
    UNKNOWN, SUSPENDED, READY;

    public static boolean isActivation(SuspendedStatus from, SuspendedStatus to) {
        return from == SUSPENDED && to == READY;
    }

    public static boolean isDesactivation(SuspendedStatus from, SuspendedStatus to) {
        return from == READY && to == SUSPENDED;
    }
}
