package com.gni.frmk.tools.addon.invoke.actions.wmroot;

import com.gni.frmk.tools.addon.invoke.actions.StringPackageAwareAction;
import com.gni.frmk.tools.addon.dispatcher.NoResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:37
 *
 * @author: e03229
 */
public class EnablePortListener extends StringPackageAwareAction<NoResult> {
    //TODO remplacer par BUILDER pour diminuer les risques dans le code
    public EnablePortListener(String packageName, String key) {
        super(packageName, key);
    }
}
