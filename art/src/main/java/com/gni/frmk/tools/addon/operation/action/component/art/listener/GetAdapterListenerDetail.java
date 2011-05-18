package com.gni.frmk.tools.addon.operation.action.component.art.listener;

import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener.AdapterListenerDetail;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentDetail;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 18:38
 *
 * @author: e03229
 */
public class GetAdapterListenerDetail
        extends GetComponentDetail<AdapterListenerDetail, AdapterId> {
    public GetAdapterListenerDetail(AdapterId id) {
        super(id);
    }
}