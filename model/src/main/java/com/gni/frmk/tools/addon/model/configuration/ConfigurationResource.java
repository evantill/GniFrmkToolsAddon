package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.model.BaseModelResource;
import com.gni.frmk.tools.addon.model.configuration.base.BaseConfiguration;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 10:23
 *
 * @author: e03229
 */
public class ConfigurationResource extends BaseModelResource {

    private static final Package[] contextPathPackages = new Package[]{
            BaseConfiguration.class.getPackage()
    };

    @Override
    protected Package[] getContextPathPackages() {
        return contextPathPackages;
    }
}
