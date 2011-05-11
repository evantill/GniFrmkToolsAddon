package com.gni.frmk.tools.addon.action.component.jms.alias;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsAlias.JmsAliasDetail;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.gni.frmk.tools.addon.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 15:57
 *
 * @author: e03229
 */
public class GetJmsAliasDetail implements Action<ComponentDetailResult<JmsAliasDetail>> {
    private final StringId id;

    public GetJmsAliasDetail(StringId id) {
        this.id = id;
    }

    public StringId getId() {
        return id;
    }
}
