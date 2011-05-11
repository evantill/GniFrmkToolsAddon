package com.gni.frmk.tools.addon.action.component.root.trigger;

import com.gni.frmk.tools.addon.model.component.NoDetail;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.gni.frmk.tools.addon.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 14:36
 *
 * @author: e03229
 */
public class GetNativeTriggerDetail implements Action<ComponentDetailResult<NoDetail>> {
    private final StringId id;

    public GetNativeTriggerDetail(StringId id) {
        this.id = id;
    }

    public StringId getId() {
        return id;
    }
}
