package com.gni.frmk.tools.addon.command.action.wm.art;

import com.gni.frmk.tools.addon.command.action.StringAction;
import com.gni.frmk.tools.addon.command.result.ListResult;
import com.gni.frmk.tools.addon.model.component.AdapterConnection;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:16
 *
 * @author: e03229
 */
public class ListAdaptersConnections extends StringAction<ListResult<AdapterConnection>> {
    public ListAdaptersConnections(String adapterType) {
        super(adapterType);
    }
}
