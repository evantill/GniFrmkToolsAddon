package com.gni.frmk.tools.addon.operation.action.component.art.connection.oldies;

import com.gni.frmk.tools.addon.model.component.PackageDetail;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:56
 *
 * @author: e03229
 */
public class GetAdapterConnectionDetail
        extends GetComponentDetail<PackageDetail, AdapterId> {
    public GetAdapterConnectionDetail(AdapterId id) {
        super(id);
    }
}
