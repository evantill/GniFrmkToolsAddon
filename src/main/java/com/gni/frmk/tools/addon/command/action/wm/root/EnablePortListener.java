package com.gni.frmk.tools.addon.command.action.wm.root;

import com.gni.frmk.tools.addon.command.action.wm.StringPackageAwareAction;
import com.gni.frmk.tools.addon.command.result.NoResult;

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
