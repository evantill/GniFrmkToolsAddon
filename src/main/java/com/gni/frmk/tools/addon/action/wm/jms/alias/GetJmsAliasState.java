package com.gni.frmk.tools.addon.action.wm.jms.alias;

import com.gni.frmk.tools.addon.model.component.state.ConnectableState;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.result.ComponentStateResult;
import com.gni.frmk.tools.addon.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 15:57
 *
 * @author: e03229
 */
public class GetJmsAliasState
        implements Action<ComponentStateResult<ConnectableState>> {
    private final StringId id;

    public GetJmsAliasState(StringId id) {
        this.id = id;
    }

    public StringId getId() {
        return id;
    }
}
