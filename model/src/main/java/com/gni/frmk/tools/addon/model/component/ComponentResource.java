package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.ModelResource;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 10:24
 *
 * @author: e03229
 */
public class ComponentResource implements ModelResource{

    @Override
    public String getContextPath() {
        return getClass().getPackage().getName();
    }
}
