package com.gni.frmk.tools.addon.operation.handler.component.root.trigger;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.NoDetail;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.NativeTrigger;
import com.gni.frmk.tools.addon.model.component.root.NativeTriggerState;
import com.gni.frmk.tools.addon.operation.action.component.root.trigger.GetNativeTrigger;
import com.gni.frmk.tools.addon.operation.action.component.root.trigger.NativeTriggerFactory;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 19:55
 *
 * @author: e03229
 */
public class GetNativeTriggerHandler
        extends GetComponentHandler<StringId, NoDetail, NativeTriggerState, NativeTrigger, GetNativeTrigger, InvokeContext> {

    public GetNativeTriggerHandler() {
        super(new NativeTriggerFactory());
    }

    @Override
    public Class<? extends GetNativeTrigger> getActionType() {
        return GetNativeTrigger.class;
    }
}
