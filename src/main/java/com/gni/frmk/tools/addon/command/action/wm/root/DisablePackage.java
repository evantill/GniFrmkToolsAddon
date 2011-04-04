package com.gni.frmk.tools.addon.command.action.wm.root;

import com.gni.frmk.tools.addon.command.action.wm.PackageAwareAction;
import com.gni.frmk.tools.addon.command.result.NoResult;

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
