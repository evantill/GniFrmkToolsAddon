package com.gni.frmk.tools.addon.model.component.art;

import com.gni.frmk.tools.addon.model.BaseModelResource;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 10:13
 *
 * @author: e03229
 */
public class ArtResource extends BaseModelResource {

    private static final Package[] contextPathPackages = new Package[]{
            AdapterConnection.class.getPackage()
    };

    @Override
    protected Package[] getContextPathPackages() {
        return contextPathPackages;
    }

}
