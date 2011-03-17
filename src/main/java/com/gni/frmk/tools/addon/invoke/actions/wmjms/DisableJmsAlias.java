package com.gni.frmk.tools.addon.invoke.actions.wmjms;

import com.gni.frmk.tools.addon.invoke.Action;
import com.gni.frmk.tools.addon.invoke.results.NoResult;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 16:59
 *
 * @author: e03229
 */
public class DisableJmsAlias implements Action<NoResult> {
    private final String aliasName;

    public DisableJmsAlias(String aliasName) {
        this.aliasName = checkNotNull(aliasName);
    }

    public String getAliasName() {
        return aliasName;
    }
}
