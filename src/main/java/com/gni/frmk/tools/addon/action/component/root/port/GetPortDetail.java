package com.gni.frmk.tools.addon.action.component.root.port;

import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.model.component.root.Port.PortDetail;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.gni.frmk.tools.addon.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 13:32
 *
 * @author: e03229
 */
public class GetPortDetail implements Action<ComponentDetailResult<PortDetail>> {

    private final PackageAndStringId id;

    public GetPortDetail(PackageAndStringId id) {
        this.id = id;
    }

    public PackageAndStringId getId() {
        return id;
    }
}