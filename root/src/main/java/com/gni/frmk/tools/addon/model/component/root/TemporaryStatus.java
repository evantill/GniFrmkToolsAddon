package com.gni.frmk.tools.addon.model.component.root;

/**
* Created by IntelliJ IDEA.
* Date: 17/05/11
* Time: 17:55
*
* @author: e03229
*/
public enum TemporaryStatus {
    UNKNOWN(false), TEMPORARY(false), PERMANENT(true);

    private final boolean permanent;

    TemporaryStatus(boolean permanent) {
        this.permanent = permanent;
    }

    public final boolean isPermanent() {
        return permanent;
    }

    public boolean isTemporary() {
        return !permanent;
    }

    public static TemporaryStatus fromBoolean(boolean permanent) {
        return permanent?PERMANENT : TEMPORARY;
    }
}
