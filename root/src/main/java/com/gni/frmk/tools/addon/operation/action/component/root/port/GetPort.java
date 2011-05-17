package com.gni.frmk.tools.addon.operation.action.component.root.port;

import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.model.component.root.Port;
import com.gni.frmk.tools.addon.operation.action.component.GetComponent;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 19:37
 *
 * @author: e03229
 */
public class GetPort extends GetComponent<Port, PackageAndStringId> {
    public GetPort(PackageAndStringId id) {
        super(id);
    }
}
