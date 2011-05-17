package com.gni.frmk.tools.addon.operation.action.component.root.port;

import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.operation.action.component.ComponentAction;
import com.gni.frmk.tools.addon.model.component.root.Port.PortDetail;
import com.gni.frmk.tools.addon.operation.result.ComponentDetailResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 13:32
 *
 * @author: e03229
 */
public class GetPortDetail implements ComponentAction<PackageAndStringId,ComponentDetailResult<PortDetail>> {

    private final PackageAndStringId id;

    public GetPortDetail(PackageAndStringId id) {
        this.id = id;
    }

    public PackageAndStringId getId() {
        return id;
    }
}