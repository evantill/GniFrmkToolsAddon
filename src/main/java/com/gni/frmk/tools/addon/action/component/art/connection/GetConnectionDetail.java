package com.gni.frmk.tools.addon.action.component.art.connection;

import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.model.component.AdapterConnection.Detail;
import com.gni.frmk.tools.addon.model.component.id.AdapterId;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;

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
