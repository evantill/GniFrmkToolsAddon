package com.gni.frmk.tools.addon.invoke.action.wmart;

import com.gni.frmk.tools.addon.configuration.component.AdapterListener;
import com.gni.frmk.tools.addon.dispatcher.ListResult;
import com.gni.frmk.tools.addon.dispatcher.StringAction;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:17
 *
 * @author: e03229
 */
public class ListListeners extends StringAction<ListResult<AdapterListener>> {
    public ListListeners(String adapterType) {
        super(adapterType);
    }
}
