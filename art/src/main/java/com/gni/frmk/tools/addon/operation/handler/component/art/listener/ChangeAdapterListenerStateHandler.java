package com.gni.frmk.tools.addon.operation.handler.component.art.listener;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.action.component.art.listener.ChangeAdapterListenerState;
import com.gni.frmk.tools.addon.operation.action.component.art.listener.DisableAdapterListener;
import com.gni.frmk.tools.addon.operation.action.component.art.listener.EnableAdapterListener;
import com.gni.frmk.tools.addon.operation.action.component.art.listener.ResumeAdapterListener;
import com.gni.frmk.tools.addon.operation.action.component.art.listener.SuspendAdapterListener;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.handler.component.ChangeComponentStateHandler;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 15:56
 *
 * @author: e03229
 */
public class ChangeAdapterListenerStateHandler
        extends ChangeComponentStateHandler<ChangeAdapterListenerState, AdapterId, ActivableState, InvokeContext> {

    @Override
    protected List<Action<?>> defineActions(ActivableState oldState, ActivableState newState, AdapterId id) {
        EnableStatus oldEnabled = oldState.getEnabled();
        EnableStatus newEnabled = newState.getEnabled();
        List<Action<?>> actions = Lists.newArrayList();
        if (EnableStatus.isEnabling(oldEnabled, newEnabled)) {
            actions.add(new EnableAdapterListener(id));
        }
        if (EnableStatus.isDisabling(oldEnabled, newEnabled)) {
            actions.add(new DisableAdapterListener(id));
        }
        ActivableStatus oldActivated = oldState.getActivable();
        ActivableStatus newActivated = newState.getActivable();
        if (ActivableStatus.isActivation(oldActivated, newActivated)) {
            actions.add(new ResumeAdapterListener(id));
        }
        if (ActivableStatus.isDesactivation(oldActivated, newActivated)) {
            actions.add(new SuspendAdapterListener(id));
        }
        return actions;
    }

    @Override
    public Class<? extends ChangeAdapterListenerState> getActionType() {
        return ChangeAdapterListenerState.class;
    }
}
