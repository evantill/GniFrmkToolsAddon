package com.gni.frmk.tools.addon.operation.action.component.jms.trigger;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger;
import com.gni.frmk.tools.addon.model.component.jms.JmsTrigger.JmsTriggerDetail;
import com.gni.frmk.tools.addon.operation.action.component.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 19:20
 *
 * @author: e03229
 */
public class JmsTriggerFactory
        implements ComponentFactory<StringId, ActivableState, JmsTriggerDetail, JmsTrigger> {

    @Override
    public JmsTrigger newComponent(StringId id, JmsTriggerDetail detail, ActivableState state) {
        JmsTrigger component = new JmsTrigger();
        component.setType(Type.JMS_TRIGGER);
        component.setId(id);
        component.setCurrentState(state);
        component.setDetail(detail);
        return component;
    }

    @Override
    public ListComponentIds<StringId> newListComponentIdsAction() {
        return new ListJmsTriggerIds();
    }

    @Override
    public GetAllComponents<JmsTrigger> newGetAllComponentAction() {
        return new GetAllJmsTrigger();
    }

    @Override
    public GetComponent<JmsTrigger, StringId> newGetComponentAction(StringId id) {
        return new GetJmsTrigger(id);
    }

    @Override
    public GetComponentDetail<JmsTriggerDetail, StringId> newGetComponentDetailAction(StringId id) {
        return new GetJmsTriggerDetail(id);
    }

    @Override
    public GetComponentState<ActivableState, StringId> newGetComponentStateAction(StringId id) {
        return new GetJmsTriggerState(id);
    }
}
