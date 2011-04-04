package com.gni.frmk.tools.addon.command.action.wm.art;

import com.gni.frmk.tools.addon.command.action.StringAction;
import com.gni.frmk.tools.addon.command.result.ListResult;
import com.gni.frmk.tools.addon.model.component.AdapterNotification;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:18
 *
 * @author: e03229
 */
public class ListNotifications extends StringAction<ListResult<AdapterNotification>> {
    public ListNotifications(String adapterType) {
        super(adapterType);
    }
}
