package com.gni.frmk.tools.addon.operation.action.component.root.trigger;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.NativeTriggerState;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 14:31
 *
 * @author: e03229
 */
public class GetNativeTriggerState
        extends GetComponentState<NativeTriggerState, StringId> {
    public GetNativeTriggerState(StringId id) {
        super(id);
    }
}
