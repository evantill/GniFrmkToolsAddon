package com.gni.frmk.tools.addon.invoke.actions.wmroot;

import com.gni.frmk.tools.addon.invoke.actions.PackageAwareAction;
import com.gni.frmk.tools.addon.invoke.results.NoResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:38
 *
 * @author: e03229
 */
public class EnablePackage extends PackageAwareAction<NoResult> {
    protected EnablePackage(String packageName) {
        super(packageName);
    }
}
