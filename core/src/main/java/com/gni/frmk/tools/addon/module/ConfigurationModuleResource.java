package com.gni.frmk.tools.addon.module;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 18:08
 *
 * @author: e03229
 */
public class ConfigurationModuleResource extends BaseModuleResource<InvokeContext>{

    @Override
    public String getContextPath() {
        return "com.gni.frmk.tools.addon.model.configuration";
    }
}
