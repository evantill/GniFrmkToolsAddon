package com.gni.frmk.tools.addon.command.action.wm.art.listener;

import com.gni.frmk.tools.addon.command.action.StringAction;
import com.gni.frmk.tools.addon.command.result.NoResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:16
 *
 * @author: e03229
 */
public class EnableListener extends StringAction<NoResult> {
    public EnableListener(String listenerName) {
        super(listenerName);
    }
}
