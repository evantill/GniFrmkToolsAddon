package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.model.ModelResource;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 10:23
 *
 * @author: e03229
 */
public class ConfigurationResource implements ModelResource{

    @Override
    public String getContextPath() {
        return getClass().getPackage().getName();
    }
}
