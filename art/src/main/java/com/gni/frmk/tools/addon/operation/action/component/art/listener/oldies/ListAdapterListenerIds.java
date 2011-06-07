package com.gni.frmk.tools.addon.operation.action.component.art.listener.oldies;

import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.oldies.ListAdapterTypeAwareComponentIds;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:17
 *
 * @author: e03229
 */
public class ListAdapterListenerIds
         extends ListAdapterTypeAwareComponentIds<AdapterId> {

    public ListAdapterListenerIds(String adapterTypeFilter) {
        super(adapterTypeFilter);
    }

    public ListAdapterListenerIds() {
    }
}