package com.gni.frmk.tools.addon.model.component.jms;

import com.gni.frmk.tools.addon.model.BaseModelResource;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 10:14
 *
 * @author: e03229
 */
public class JmsResource extends BaseModelResource {

    private static final Package[] contextPathPackages = new Package[]{
            JmsAlias.class.getPackage()
    };

    @Override
    protected Package[] getContextPathPackages() {
        return contextPathPackages;
    }
}
