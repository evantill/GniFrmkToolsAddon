package com.gni.frmk.tools.addon.action.component.art.listener;

import com.gni.frmk.tools.addon.action.component.art.AbstractAdapterTypeAwareAction;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:17
 *
 * @author: e03229
 */
public class ListenerIdList
        extends AbstractAdapterTypeAwareAction<AdapterId> {

    public ListenerIdList(String adapterTypeFilter) {
        super(adapterTypeFilter);
    }

    public ListenerIdList() {
    }
}