package com.gni.frmk.tools.addon.action.wm.root.port;

import com.gni.frmk.tools.addon.model.PackageAndStringId;
import com.gni.frmk.tools.addon.model.component.Port.Detail;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import ev.frmk.tools.plateform.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 13:32
 *
 * @author: e03229
 */
public class GetPortDetail implements Action<ComponentDetailResult<Detail>> {

    private final PackageAndStringId id;

    public GetPortDetail(PackageAndStringId id) {
        this.id = id;
    }

    public PackageAndStringId getId() {
        return id;
    }
}