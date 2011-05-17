package com.gni.frmk.tools.addon.operation.action.component.jms.alias;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.operation.action.component.ComponentAction;
import com.gni.frmk.tools.addon.operation.result.NoResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 16:59
 *
 * @author: e03229
 */
public class DisableJmsAlias implements ComponentAction<StringId,NoResult> {
    private final StringId id;

    public DisableJmsAlias(StringId aliasName) {
        this.id = aliasName;
    }

    public StringId getId() {
        return id;
    }
}
