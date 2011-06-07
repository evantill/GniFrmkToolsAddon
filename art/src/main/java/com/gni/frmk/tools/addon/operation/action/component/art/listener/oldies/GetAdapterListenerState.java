package com.gni.frmk.tools.addon.operation.action.component.art.listener.oldies;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 18:38
 *
 * @author: e03229
 */
public class GetAdapterListenerState extends GetComponentState<ActivableState, AdapterId> {
    public GetAdapterListenerState(AdapterId id) {
        super(id);
    }
}
