package com.gni.frmk.tools.addon.model.component.id;

import com.gni.frmk.tools.addon.model.component.BaseComponent.AbstractId;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 13:37
 *
 * @author: e03229
 */
public class PackageAndStringId extends AbstractId {
    private String packageName;
    private String id;

    public PackageAndStringId(String packageName, String id) {
        this.packageName = packageName;
        this.id = id;
    }

    public PackageAndStringId() {
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
