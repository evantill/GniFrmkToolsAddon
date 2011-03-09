package com.gni.frmk.tools.addon.data3.components.integrationServer;

import com.gni.frmk.tools.addon.data3.components.Component;
import com.gni.frmk.tools.addon.data3.components.ComponentType;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 07/03/11
 * Time: 18:54
 * To change this template use File | Settings | File Templates.
 */
public abstract class PackageComponent extends AbstractComponent implements Component {
    private final String packageName;

    protected  PackageComponent(ComponentType type, Id id, List<Information> informations, String packageName) {
        super(type, id, informations);
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

}
