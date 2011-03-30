package com.gni.frmk.tools.addon.invoke.action.wmart;

import com.gni.frmk.tools.addon.dispatcher.NoResult;
import com.gni.frmk.tools.addon.dispatcher.StringAction;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:15
 *
 * @author: e03229
 */
public class DisableConnection extends StringAction<NoResult> {
    public DisableConnection(String aliasName) {
        super(aliasName);
    }
}
