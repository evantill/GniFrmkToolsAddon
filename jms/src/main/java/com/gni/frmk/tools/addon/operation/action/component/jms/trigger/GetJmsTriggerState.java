package com.gni.frmk.tools.addon.operation.action.component.jms.trigger;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 15:56
 *
 * @author: e03229
 */
public class GetJmsTriggerState
    extends GetComponentState<ActivableState, StringId>
{
    public GetJmsTriggerState(StringId id) {
        super(id);
    }
}
