package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.BaseModelResource;
import com.gni.frmk.tools.addon.model.component.base.BaseComponent;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 10:24
 *
 * @author: e03229
 */
public class ComponentResource extends BaseModelResource {
    private static final Package[] contextPathPackages = new Package[]{
            Component.class.getPackage(),
            BaseComponent.class.getPackage()
    };

    @Override
    protected Package[] getContextPathPackages() {
        return contextPathPackages;
    }
}
