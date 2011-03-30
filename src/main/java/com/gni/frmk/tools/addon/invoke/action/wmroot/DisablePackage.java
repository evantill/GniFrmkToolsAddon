package com.gni.frmk.tools.addon.invoke.action.wmroot;

import com.gni.frmk.tools.addon.dispatcher.NoResult;
import com.gni.frmk.tools.addon.invoke.action.PackageAwareAction;

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
