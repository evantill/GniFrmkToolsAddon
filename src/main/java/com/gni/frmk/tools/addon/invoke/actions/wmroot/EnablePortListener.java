package com.gni.frmk.tools.addon.invoke.actions.wmroot;

import com.gni.frmk.tools.addon.invoke.actions.StringPackageAwareAction;
import com.gni.frmk.tools.addon.invoke.results.NoResult;
import com.gni.frmk.tools.addon.invoke.actions.StringAction;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:37
 *
 * @author: e03229
 */
public class EnablePortListener extends StringPackageAwareAction<NoResult> {
    protected EnablePortListener(String packageName, String parameter) {
        super(packageName, parameter);
    }
}
