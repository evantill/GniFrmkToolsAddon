package com.gni.frmk.tools.addon.operation.action.component.jms.trigger;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger;
import com.gni.frmk.tools.addon.operation.action.component.ChangeComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 14:26
 *
 * @author: e03229
 */
public class ChangeJmsTriggerState extends ChangeComponentState<StringId, ActivableState> {

    public ChangeJmsTriggerState(ActivableState newComponentState, JmsTrigger component) {
        super(newComponentState, component);
    }
}
