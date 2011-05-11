package com.gni.frmk.tools.addon.handler.component.jms.trigger;

import com.gni.frmk.tools.addon.action.component.jms.alias.JmsAliasIdList;
import com.gni.frmk.tools.addon.action.component.jms.trigger.GetJmsTriggerDetail;
import com.gni.frmk.tools.addon.action.component.jms.trigger.GetJmsTriggerState;
import com.gni.frmk.tools.addon.action.component.jms.trigger.JmsTriggerList;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.api.action.DispatchException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.component.art.AbstractComponentListHandler;
import com.gni.frmk.tools.addon.model.Component.Type;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger.JmsTriggerDetail;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.gni.frmk.tools.addon.result.ComponentStateResult;
import com.gni.frmk.tools.addon.result.ListResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 18:04
 *
 * @author: e03229
 */
public class JmsTriggerListHandler
        extends AbstractComponentListHandler<StringId, JmsTriggerDetail, ActivableState, JmsTrigger, JmsTriggerList, InvokeContext>
        implements ActionHandler<JmsTriggerList, ListResult<JmsTrigger>, InvokeContext> {

    @Override
    public Class<JmsTriggerList> getActionType() {
        return JmsTriggerList.class;
    }

    @Override
    protected Action<ListResult<StringId>> newListIdAction() {
        return new JmsAliasIdList();
    }

    @Override
    protected Action<ComponentDetailResult<JmsTriggerDetail>> newGetComponentDetailAction(StringId id) {
        return new GetJmsTriggerDetail(id);
    }

    @Override
    protected Action<ComponentStateResult<ActivableState>> newGetComponentStateAction(StringId id) {
        return new GetJmsTriggerState(id);
    }

    @Override
    protected JmsTrigger newComponent(StringId id, JmsTriggerDetail detail, ActivableState state) throws DispatchException {
        JmsTrigger component = new JmsTrigger();
        component.setType(Type.JMS_TRIGGER);
        component.setId(id);
        component.setDetail(detail);
        component.setCurrentState(state);
        return component;
    }
}
