package com.gni.frmk.tools.addon.operation.action.component.root.trigger;

import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.NativeTrigger;
import com.gni.frmk.tools.addon.model.component.root.NativeTriggerState;
import com.gni.frmk.tools.addon.operation.action.component.ChangeComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 11:56
 *
 * @author: e03229
 */
public class ChangeNativeTriggerState
        extends ChangeComponentState<StringId, NativeTriggerState> {
    public ChangeNativeTriggerState(NativeTriggerState newComponentState, NativeTrigger component) {
        super(newComponentState, component);
    }
}
