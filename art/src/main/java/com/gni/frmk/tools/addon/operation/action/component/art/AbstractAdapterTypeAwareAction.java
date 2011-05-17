package com.gni.frmk.tools.addon.operation.action.component.art;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.handler.component.art.AdapterTypeAwareAction;
import com.gni.frmk.tools.addon.model.component.Component.Id;
import com.gni.frmk.tools.addon.operation.result.ListResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 17:44
 *
 * @author: e03229
 */
public class AbstractAdapterTypeAwareAction<I extends Id>
        implements Action<ListResult<I>>, AdapterTypeAwareAction {

    private final String adapterTypeFilter;
    private final boolean filtered;

    protected AbstractAdapterTypeAwareAction(String adapterTypeFilter) {
        this.adapterTypeFilter = adapterTypeFilter;
        filtered = true;
    }

    protected AbstractAdapterTypeAwareAction() {
        filtered = false;
        adapterTypeFilter = null;
    }

    public String getAdapterTypeFilter() {
        return adapterTypeFilter;
    }

    public boolean isFiltered() {
        return filtered;
    }
}
