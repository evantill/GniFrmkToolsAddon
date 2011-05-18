package com.gni.frmk.tools.addon.operation.action.component.art.connection;

import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:21
 *
 * @author: e03229
 */
public class GetAdapterConnectionState
        extends GetComponentState<EnableState, AdapterId> {
    public GetAdapterConnectionState(AdapterId id) {
        super(id);
    }
}
