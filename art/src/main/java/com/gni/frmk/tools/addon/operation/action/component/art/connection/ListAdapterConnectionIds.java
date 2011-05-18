package com.gni.frmk.tools.addon.operation.action.component.art.connection;

import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.ListAdapterTypeAwareComponentIds;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:16
 *
 * @author: e03229
 */
public class ListAdapterConnectionIds
        extends ListAdapterTypeAwareComponentIds<AdapterId> {

    public ListAdapterConnectionIds(String adapterTypeFilter) {
        super(adapterTypeFilter);
    }

    public ListAdapterConnectionIds() {
    }
}
