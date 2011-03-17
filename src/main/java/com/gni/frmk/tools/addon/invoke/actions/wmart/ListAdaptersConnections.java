package com.gni.frmk.tools.addon.invoke.actions.wmart;

import com.gni.frmk.tools.addon.configuration.components.AdapterConnection;
import com.gni.frmk.tools.addon.invoke.actions.StringAction;
import com.gni.frmk.tools.addon.invoke.results.ListResult;

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
