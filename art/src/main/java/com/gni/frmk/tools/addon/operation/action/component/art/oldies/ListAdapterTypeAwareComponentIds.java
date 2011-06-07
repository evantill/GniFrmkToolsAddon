package com.gni.frmk.tools.addon.operation.action.component.art.oldies;

import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.operation.action.component.__ListComponentIds;
import com.gni.frmk.tools.addon.operation.handler.component.art.ListAdapterTypeAwareIdsAction;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 17:44
 *
 * @author: e03229
 */
public class ListAdapterTypeAwareComponentIds<I extends ComponentId>
        extends __ListComponentIds<I>
        implements ListAdapterTypeAwareIdsAction {

    private final String adapterTypeFilter;
    private final boolean filtered;

    protected ListAdapterTypeAwareComponentIds(String adapterTypeFilter) {
        this.adapterTypeFilter = adapterTypeFilter;
        filtered = true;
    }

    protected ListAdapterTypeAwareComponentIds() {
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
