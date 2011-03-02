package com.gni.frmk.tools.addon.data.adapter;

import javax.xml.bind.annotation.XmlType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 10/11/10
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
@XmlType
public class AdapterConnectionInfo extends AdapterInfo<AdapterConnectionInfo> {

    private static final String ALIAS_KEY = "alias";

    public AdapterConnectionInfo(String adapterType, String packageName, String alias) {
        super(packageName, adapterType);
        setAlias(alias);
    }

    public String getAlias() {
        return getRequiredInfo(ALIAS_KEY);
    }

    public void setAlias(String alias) {
        addInfo(ALIAS_KEY, checkNotNull(alias));
    }

}