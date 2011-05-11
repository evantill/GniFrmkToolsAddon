package com.gni.frmk.tools.addon.action.wm.root.port;

import com.gni.frmk.tools.addon.model.component.id.PackageAndStringId;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.result.ComponentStateResult;
import com.gni.frmk.tools.addon.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 13:32
 *
 * @author: e03229
 */
public class GetPortState implements Action<ComponentStateResult<ActivableState>> {

    private final PackageAndStringId id;

    public GetPortState(PackageAndStringId id) {
        this.id = id;
    }

    public PackageAndStringId getId() {
        return id;
    }
}