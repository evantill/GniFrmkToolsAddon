package com.gni.frmk.tools.addon.command.action.wm.art;

import com.gni.frmk.tools.addon.command.action.StringAction;
import com.gni.frmk.tools.addon.command.result.ListResult;
import com.gni.frmk.tools.addon.model.component.AdapterListener;

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
