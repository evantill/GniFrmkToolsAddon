package com.gni.frmk.tools.addon.data.adapter;

import com.gni.frmk.tools.addon.data.component.ComponentPackageInfo;

import javax.xml.bind.annotation.XmlType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 10/11/10
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */
@XmlType
public class AdapterInfo<C extends AdapterInfo> extends ComponentPackageInfo<C> {
    private static final String ADAPTER_TYPE_KEY = "adapterType";

    public AdapterInfo(String packageName, String adapterType) {
        super(packageName);
        setAdapterType(adapterType);
    }

    public String getAdapterType() {
        return getRequiredInfo(ADAPTER_TYPE_KEY);
    }

    public void setAdapterType(String adapterType) {
        addInfo(ADAPTER_TYPE_KEY, checkNotNull(adapterType));
    }

}
