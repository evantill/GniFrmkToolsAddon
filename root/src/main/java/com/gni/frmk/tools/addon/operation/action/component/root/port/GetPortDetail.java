package com.gni.frmk.tools.addon.operation.action.component.root.port;

import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.model.component.root.Port.PortDetail;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 13:32
 *
 * @author: e03229
 */
public class GetPortDetail
        extends GetComponentDetail<PortDetail, PackageAndStringId> {
    public GetPortDetail(PackageAndStringId id) {
        super(id);
    }
}