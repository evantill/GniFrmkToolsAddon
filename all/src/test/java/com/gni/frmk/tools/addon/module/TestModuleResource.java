package com.gni.frmk.tools.addon.module;

import com.gni.frmk.tools.addon.operation.context.InvokeContext;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 18:23
 *
 * @author: e03229
 */
public class TestModuleResource extends BaseModuleResource<InvokeContext>{

    @Override
    public String getContextPath() {
        return "com.gni.frmk.tools.addon.model.component.test";
    }
}
