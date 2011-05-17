package com.gni.frmk.tools.addon.operation.action.component.root.port;

import com.gni.frmk.tools.addon.operation.action.component.ComponentAction;
import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.operation.result.NoResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:37
 *
 * @author: e03229
 */
public class DisablePortListener implements ComponentAction<PackageAndStringId,NoResult> {

    private final PackageAndStringId id;

    public DisablePortListener(PackageAndStringId id) {
        this.id = id;
    }

    public PackageAndStringId getId() {
        return id;
    }
}
