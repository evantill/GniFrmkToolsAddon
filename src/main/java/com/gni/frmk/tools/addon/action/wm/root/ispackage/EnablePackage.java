package com.gni.frmk.tools.addon.action.wm.root.ispackage;

import com.gni.frmk.tools.addon.action.wm.PackageAwareAction;
import com.gni.frmk.tools.addon.result.NoResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:38
 *
 * @author: e03229
 */
public class EnablePackage extends PackageAwareAction<NoResult> {
    public EnablePackage(String packageName) {
        super(packageName);
    }
}