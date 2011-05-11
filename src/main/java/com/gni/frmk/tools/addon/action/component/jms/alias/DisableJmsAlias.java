package com.gni.frmk.tools.addon.action.component.jms.alias;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.result.NoResult;
import com.gni.frmk.tools.addon.api.action.Action;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 16:59
 *
 * @author: e03229
 */
public class DisableJmsAlias implements Action<NoResult> {
    private final StringId aliasName;

    public DisableJmsAlias(StringId aliasName) {
        this.aliasName = aliasName;
    }

    public StringId getAliasName() {
        return aliasName;
    }
}
