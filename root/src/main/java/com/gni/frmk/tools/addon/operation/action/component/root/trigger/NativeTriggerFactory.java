package com.gni.frmk.tools.addon.operation.action.component.root.trigger;

import com.gni.frmk.tools.addon.model.component.Component.Type;
import com.gni.frmk.tools.addon.model.component.NoDetail;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.NativeTrigger;
import com.gni.frmk.tools.addon.model.component.root.NativeTriggerState;
import com.gni.frmk.tools.addon.operation.action.component.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 19:45
 *
 * @author: e03229
 */
public class NativeTriggerFactory
        implements ComponentFactory<StringId, NativeTriggerState, NoDetail, NativeTrigger> {

    @Override
    public NativeTrigger newComponent(StringId id, NoDetail detail, NativeTriggerState state) {
        NativeTrigger component = new NativeTrigger();
        component.setType(Type.NATIVE_TRIGGER);
        component.setId(id);
        component.setCurrentState(state);
        component.setDetail(detail);
        return component;
    }

    @Override
    public ListComponentIds<StringId> newListComponentIdsAction() {
        return new ListNativeTriggerIds();
    }

    @Override
    public GetAllComponents<NativeTrigger> newGetAllComponentAction() {
        return new GetAllNativeTriggers();
    }

    @Override
    public GetComponent<NativeTrigger, StringId> newGetComponentAction(StringId id) {
        return new GetNativeTrigger(id);
    }

    @Override
    public GetComponentDetail<NoDetail, StringId> newGetComponentDetailAction(StringId id) {
        return new GetNativeTriggerDetail(id);
    }

    @Override
    public GetComponentState<NativeTriggerState, StringId> newGetComponentStateAction(StringId id) {
        return new GetNativeTriggerState(id);
    }
}
