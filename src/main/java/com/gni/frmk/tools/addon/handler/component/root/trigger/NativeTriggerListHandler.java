package com.gni.frmk.tools.addon.handler.component.root.trigger;

import com.gni.frmk.tools.addon.action.component.root.trigger.GetNativeTriggerDetail;
import com.gni.frmk.tools.addon.action.component.root.trigger.GetNativeTriggerState;
import com.gni.frmk.tools.addon.action.component.root.trigger.NativeTriggerIdList;
import com.gni.frmk.tools.addon.action.component.root.trigger.NativeTriggerList;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.api.action.DispatchException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.component.art.AbstractComponentListHandler;
import com.gni.frmk.tools.addon.model.Component.Type;
import com.gni.frmk.tools.addon.model.component.NoDetail;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.NativeTrigger;
import com.gni.frmk.tools.addon.model.component.root.NativeTriggerState;
import com.gni.frmk.tools.addon.result.ComponentDetailResult;
import com.gni.frmk.tools.addon.result.ComponentStateResult;
import com.gni.frmk.tools.addon.result.ListResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/05/11
 * Time: 18:25
 *
 * @author: e03229
 */
public class NativeTriggerListHandler
        extends AbstractComponentListHandler<StringId, NoDetail, NativeTriggerState, NativeTrigger, NativeTriggerList, InvokeContext>
        implements ActionHandler<NativeTriggerList, ListResult<NativeTrigger>, InvokeContext> {

    @Override
    public Class<NativeTriggerList> getActionType() {
        return NativeTriggerList.class;
    }

    @Override
    protected Action<ListResult<StringId>> newListIdAction() {
        return new NativeTriggerIdList();
    }

    @Override
    protected Action<ComponentDetailResult<NoDetail>> newGetComponentDetailAction(StringId id) {
        return new GetNativeTriggerDetail(id);
    }

    @Override
    protected Action<ComponentStateResult<NativeTriggerState>> newGetComponentStateAction(StringId id) {
        return new GetNativeTriggerState(id);
    }

    @Override
    protected NativeTrigger newComponent(StringId id, NoDetail detail, NativeTriggerState state) throws DispatchException {
        NativeTrigger component = new NativeTrigger();
        component.setType(Type.NATIVE_TRIGGER);
        component.setId(id);
        component.setDetail(detail);
        component.setCurrentState(state);
        return component;
    }
}