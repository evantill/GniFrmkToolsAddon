package com.gni.frmk.tools.addon.invoke.wmart;

import com.gni.frmk.tools.addon.configuration.components.AdapterListener;
import com.gni.frmk.tools.addon.invoke.ListResult;
import com.gni.frmk.tools.addon.invoke.StringAction;

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
