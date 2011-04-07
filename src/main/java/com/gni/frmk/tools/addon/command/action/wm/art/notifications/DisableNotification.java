package com.gni.frmk.tools.addon.command.action.wm.art.notifications;

import com.gni.frmk.tools.addon.command.action.StringAction;
import com.gni.frmk.tools.addon.command.result.NoResult;

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
