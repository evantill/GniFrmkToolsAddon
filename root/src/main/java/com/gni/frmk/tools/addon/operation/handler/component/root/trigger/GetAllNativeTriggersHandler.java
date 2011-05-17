package com.gni.frmk.tools.addon.operation.handler.component.root.trigger;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.NoDetail;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.NativeTrigger;
import com.gni.frmk.tools.addon.model.component.root.NativeTriggerState;
import com.gni.frmk.tools.addon.operation.action.component.root.trigger.GetAllNativeTriggers;
import com.gni.frmk.tools.addon.operation.action.component.root.trigger.NativeTriggerFactory;
import com.gni.frmk.tools.addon.operation.handler.component.GetAllComponentsHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 18:25
 *
 * @author: e03229
 */
public class GetAllNativeTriggersHandler
        extends GetAllComponentsHandler<StringId, NoDetail, NativeTriggerState, NativeTrigger, GetAllNativeTriggers, InvokeContext> {

    public GetAllNativeTriggersHandler() {
        super(new NativeTriggerFactory());
    }

    @Override
    public Class<? extends GetAllNativeTriggers> getActionType() {
        return GetAllNativeTriggers.class;
    }
}