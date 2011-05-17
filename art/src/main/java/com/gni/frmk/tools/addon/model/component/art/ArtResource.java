package com.gni.frmk.tools.addon.model.component.art;

import com.gni.frmk.tools.addon.model.ModelResource;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 10:13
 *
 * @author: e03229
 */
public class ArtResource implements ModelResource {

    @Override
    public String getContextPath() {
        return getClass().getPackage().getName();
    }
}
