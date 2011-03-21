package com.gni.frmk.tools.addon.invoke.actions;

import com.gni.frmk.tools.addon.dispatcher.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 10:01
 *
 * @author: e03229
 */
public abstract class StringPackageAwareAction<R extends Result> extends  PackageAwareAction<R>{
    private final String parameter;

    protected StringPackageAwareAction(String packageName, String parameter) {
        super(packageName);
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
