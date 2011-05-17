package com.gni.frmk.tools.addon.operation.action.component.art.connection;

import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.ComponentAction;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection.AdapterConnectionDetail;
import com.gni.frmk.tools.addon.operation.result.ComponentDetailResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:56
 *
 * @author: e03229
 */
public class GetConnectionDetail implements ComponentAction<AdapterId,ComponentDetailResult<AdapterConnectionDetail>> {
    private final AdapterId id;

    public GetConnectionDetail(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }

}
