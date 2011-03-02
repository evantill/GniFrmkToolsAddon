package com.gni.frmk.tools.addon.data.component;

import javax.xml.bind.annotation.XmlType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 10/11/10
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 */
@XmlType
public class ComponentPackageInfo<C extends ComponentPackageInfo> extends ComponentInfo<C> {

    private static final String PACKAGE_NAME_KEY = "packageName";

    public ComponentPackageInfo(String packageName) {
        setPackageName(packageName);
    }

    public String getPackageName() {
        return getRequiredInfo(PACKAGE_NAME_KEY);
    }

    public void setPackageName(String packageName) {
        addInfo(PACKAGE_NAME_KEY, checkNotNull(packageName));
    }

}
