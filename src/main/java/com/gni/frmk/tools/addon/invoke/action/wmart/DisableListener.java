package com.gni.frmk.tools.addon.invoke.action.wmart;

import com.gni.frmk.tools.addon.dispatcher.StringAction;
import com.gni.frmk.tools.addon.dispatcher.NoResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:16
 *
 * @author: e03229
 */
public class DisableListener extends StringAction<NoResult> {
    public DisableListener(String listenerName) {
        super(listenerName);
    }
}
