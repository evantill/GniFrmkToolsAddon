package com.gni.frmk.tools.addon.data3.components.integrationServer;

import com.gni.frmk.tools.addon.data3.components.ComponentType;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 07/03/11
 * Time: 18:54
 * To change this template use File | Settings | File Templates.
 */
public abstract class AdapterTypeComponent extends PackageComponent{
    private final String adapterType;

    public AdapterTypeComponent(ComponentType type, Id id, List<Information> informations, String packageName, String adapterType) {
        super(type, id, informations, packageName);
        this.adapterType = adapterType;
    }

    public String getAdapterType() {
        return adapterType;
    }
}
