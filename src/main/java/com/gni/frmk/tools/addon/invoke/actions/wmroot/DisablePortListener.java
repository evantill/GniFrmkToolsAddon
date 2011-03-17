package com.gni.frmk.tools.addon.invoke.actions.wmroot;

import com.gni.frmk.tools.addon.invoke.actions.StringPackageAwareAction;
import com.gni.frmk.tools.addon.invoke.results.NoResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:37
 *
 * @author: e03229
 */
public class DisablePortListener extends StringPackageAwareAction<NoResult> {
    //TODO remplacer par BUILDER pour diminuer les risques dans le code
    public DisablePortListener(String packageName, String key) {
        super(packageName, key);
    }
}
