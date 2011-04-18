package com.gni.frmk.tools.addon.action.wm.art.notifications;

import com.gni.frmk.tools.addon.action.StringAction;
import com.gni.frmk.tools.addon.result.NoResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:17
 *
 * @author: e03229
 */
public class EnableNotification extends StringAction<NoResult> {
    public EnableNotification(String notificationName) {
        super(notificationName);
    }
}
