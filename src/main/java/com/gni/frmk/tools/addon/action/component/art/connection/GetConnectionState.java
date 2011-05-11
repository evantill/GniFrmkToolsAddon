package com.gni.frmk.tools.addon.action.component.art.connection;

import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.result.ComponentStateResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:21
 *
 * @author: e03229
 */
public class GetConnectionState implements Action<ComponentStateResult<EnableState>> {

    private final AdapterId id;

    public GetConnectionState(AdapterId id) {
        this.id = id;
    }

    public AdapterId getId() {
        return id;
    }

}
