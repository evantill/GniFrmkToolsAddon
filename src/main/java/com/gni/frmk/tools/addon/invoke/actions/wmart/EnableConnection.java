package com.gni.frmk.tools.addon.invoke.actions.wmart;

import com.gni.frmk.tools.addon.dispatcher.NoResult;
import com.gni.frmk.tools.addon.dispatcher.StringAction;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:15
 *
 * @author: e03229
 */
public class EnableConnection extends StringAction<NoResult> {
    public EnableConnection(String aliasName) {
        super(aliasName);
    }
}
