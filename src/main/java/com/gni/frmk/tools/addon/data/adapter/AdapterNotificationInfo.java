package com.gni.frmk.tools.addon.data.adapter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 10/11/10
 * Time: 17:04
 * To change this template use File | Settings | File Templates.
 */
public class AdapterNotificationInfo<C extends AdapterNotificationInfo> extends AdapterInfo<C> {

    private static final String NAME_KEY = "name";

    public AdapterNotificationInfo(String adapterType, String packageName, String name) {
        super(packageName, adapterType);
        setName(name);
    }

    public String getName() {
        return getRequiredInfo(NAME_KEY);
    }

    public void setName(String name) {
        addInfo(NAME_KEY, checkNotNull(name));
    }
}
