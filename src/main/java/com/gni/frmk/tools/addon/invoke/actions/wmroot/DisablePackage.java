package com.gni.frmk.tools.addon.invoke.actions.wmroot;

import com.gni.frmk.tools.addon.dispatcher.NoResult;
import com.gni.frmk.tools.addon.invoke.actions.PackageAwareAction;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:38
 *
 * @author: e03229
 */
public class DisablePackage extends PackageAwareAction<NoResult> {

    public DisablePackage(String packageName) {
        super(packageName);
    }
}
