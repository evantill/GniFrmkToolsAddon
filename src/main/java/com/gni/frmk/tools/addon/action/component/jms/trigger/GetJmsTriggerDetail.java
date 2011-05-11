package com.gni.frmk.tools.addon.action.component.jms.trigger;

import com.gni.frmk.tools.addon.model.component.JmsTrigger;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.gni.frmk.tools.addon.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 15:56
 *
 * @author: e03229
 */
public class GetJmsTriggerDetail implements Action<ComponentDetailResult<JmsTrigger.Detail>> {
    private final StringId id;

    public GetJmsTriggerDetail(StringId id) {
        this.id = id;
    }

    public StringId getId() {
        return id;
    }
}
