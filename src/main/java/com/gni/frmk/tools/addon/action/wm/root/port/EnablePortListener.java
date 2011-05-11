package com.gni.frmk.tools.addon.action.wm.root.port;

import com.gni.frmk.tools.addon.model.component.id.PackageAndStringId;
import com.gni.frmk.tools.addon.result.NoResult;
import com.gni.frmk.tools.addon.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:37
 *
 * @author: e03229
 */
public class EnablePortListener implements Action<NoResult> {
    private final PackageAndStringId id;

    public EnablePortListener(PackageAndStringId id) {
        this.id = id;
    }

    public PackageAndStringId getId() {
        return id;
    }
}