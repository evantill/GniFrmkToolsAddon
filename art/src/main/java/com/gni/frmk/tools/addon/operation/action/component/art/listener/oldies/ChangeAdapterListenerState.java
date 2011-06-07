package com.gni.frmk.tools.addon.operation.action.component.art.listener.oldies;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener;
import com.gni.frmk.tools.addon.operation.action.component.ChangeComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 15:39
 *
 * @author: e03229
 */
public class ChangeAdapterListenerState
        extends ChangeComponentState<AdapterId, ActivableState> {
    public ChangeAdapterListenerState(ActivableState newComponentState, AdapterListener component) {
        super(newComponentState, component);
    }
}
