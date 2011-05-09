package com.gni.frmk.tools.addon.action.wm;

import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:58
 *
 * @author: e03229
 */
public abstract class PackageAwareAction<R extends Result> implements Action<R> {
    private final String packageName;

    protected PackageAwareAction(String packageName) {this.packageName = packageName;}

    public String getPackageName() {
        return packageName;
    }
}