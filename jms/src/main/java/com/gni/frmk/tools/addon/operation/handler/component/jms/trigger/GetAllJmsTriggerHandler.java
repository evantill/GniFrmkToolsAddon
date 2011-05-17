package com.gni.frmk.tools.addon.operation.handler.component.jms.trigger;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger.JmsTriggerDetail;
import com.gni.frmk.tools.addon.operation.action.component.jms.trigger.GetAllJmsTrigger;
import com.gni.frmk.tools.addon.operation.action.component.jms.trigger.JmsTriggerFactory;
import com.gni.frmk.tools.addon.operation.handler.component.GetAllComponentsHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 19:25
 *
 * @author: e03229
 */
public class GetAllJmsTriggerHandler
        extends GetAllComponentsHandler<StringId, JmsTriggerDetail, ActivableState, JmsTrigger, GetAllJmsTrigger, InvokeContext> {

    public GetAllJmsTriggerHandler() {
        super(new JmsTriggerFactory());
    }

    @Override
    public Class<? extends GetAllJmsTrigger> getActionType() {
        return GetAllJmsTrigger.class;
    }
}