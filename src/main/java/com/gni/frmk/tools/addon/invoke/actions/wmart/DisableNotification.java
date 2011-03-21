package com.gni.frmk.tools.addon.invoke.actions.wmart;

import com.gni.frmk.tools.addon.dispatcher.StringAction;
import com.gni.frmk.tools.addon.dispatcher.NoResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:17
 *
 * @author: e03229
 */
public class DisableNotification extends StringAction<NoResult> {
    public DisableNotification(String notificationName) {
        super(notificationName);
    }
}
