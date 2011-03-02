package com.gni.frmk.tools.addon.data.essai.essai;

import com.gni.frmk.tools.addon.data.component.ComponentId;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 05/11/10
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */
public class PackageComponentId extends ComponentId {
    private final String packageName;

    public PackageComponentId(String name, String packageName) {
        super(name);
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }
}
