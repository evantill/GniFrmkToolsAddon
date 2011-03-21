package com.gni.frmk.tools.addon.invoke.actions.wmart;

import com.gni.frmk.tools.addon.configuration.components.AdapterNotification;
import com.gni.frmk.tools.addon.dispatcher.StringAction;
import com.gni.frmk.tools.addon.dispatcher.ListResult;

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
