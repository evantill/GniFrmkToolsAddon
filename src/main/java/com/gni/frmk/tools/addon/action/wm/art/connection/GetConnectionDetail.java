package com.gni.frmk.tools.addon.action.wm.art.connection;

import com.gni.frmk.tools.addon.model.AdapterId;
import com.gni.frmk.tools.addon.model.component.AdapterConnection.Detail;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import ev.frmk.tools.plateform.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:56
 *
 * @author: e03229
 */
public class GetConnectionDetail implements Action<ComponentDetailResult<Detail>> {
    private final AdapterId id;

    public GetConnectionDetail(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }

}
