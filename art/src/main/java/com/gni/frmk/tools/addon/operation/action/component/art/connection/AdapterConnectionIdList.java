package com.gni.frmk.tools.addon.operation.action.component.art.connection;

import com.gni.frmk.tools.addon.operation.action.component.art.AbstractAdapterTypeAwareAction;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:16
 *
 * @author: e03229
 */
public class AdapterConnectionIdList extends AbstractAdapterTypeAwareAction<AdapterId> {

    public AdapterConnectionIdList(String adapterTypeFilter) {
        super(adapterTypeFilter);
    }

    public AdapterConnectionIdList() {
    }
}
