package com.gni.frmk.tools.addon.operation.handler.component.jms.trigger;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger.JmsTriggerDetail;
import com.gni.frmk.tools.addon.operation.action.component.jms.trigger.GetJmsTrigger;
import com.gni.frmk.tools.addon.operation.action.component.jms.trigger.JmsTriggerFactory;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 19:26
 *
 * @author: e03229
 */
public class GetJmsTriggerHandler
        extends GetComponentHandler<StringId, JmsTriggerDetail, ActivableState, JmsTrigger, GetJmsTrigger, InvokeContext> {

    public GetJmsTriggerHandler() {
        super(new JmsTriggerFactory());
    }

    @Override
    public Class<? extends GetJmsTrigger> getActionType() {
        return GetJmsTrigger.class;
    }
}
