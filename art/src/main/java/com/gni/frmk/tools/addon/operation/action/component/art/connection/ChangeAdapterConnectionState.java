package com.gni.frmk.tools.addon.operation.action.component.art.connection;

import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.ChangeComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 15:05
 *
 * @author: e03229
 */
public class ChangeAdapterConnectionState
        extends ChangeComponentState<AdapterId, EnableState> {
    public ChangeAdapterConnectionState(EnableState newComponentState, AdapterConnection component) {
        super(newComponentState, component);
    }
}
