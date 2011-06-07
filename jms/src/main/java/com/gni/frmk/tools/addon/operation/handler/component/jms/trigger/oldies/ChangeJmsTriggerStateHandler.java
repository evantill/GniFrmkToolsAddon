package com.gni.frmk.tools.addon.operation.handler.component.jms.trigger.oldies;

import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.operation.action.component.jms.trigger.ChangeJmsTriggerState;
import com.gni.frmk.tools.addon.operation.action.component.jms.trigger.DisableJmsTriggers;
import com.gni.frmk.tools.addon.operation.action.component.jms.trigger.EnableJmsTriggers;
import com.gni.frmk.tools.addon.operation.action.component.jms.trigger.SuspendJmsTriggers;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.handler.component.oldies.ChangeComponentStateHandler;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/05/11
 * Time: 14:25
 *
 * @author: e03229
 */
public class ChangeJmsTriggerStateHandler
        extends ChangeComponentStateHandler<ChangeJmsTriggerState, StringId, ActivableState, ExecutionContext> {

    @Override
    protected List<Action<?>> defineActions(ActivableState oldState, ActivableState newState, StringId id) {
        List<Action<?>> actions = Lists.newArrayList();
        EnableStatus oldEnable = oldState.getEnabled();
        EnableStatus newEnable = newState.getEnabled();
        if (EnableStatus.isEnabling(oldEnable, newEnable)) {
            actions.add(new EnableJmsTriggers(id));
        }
        if (EnableStatus.isDisabling(oldEnable, newEnable)) {
            actions.add(new DisableJmsTriggers(id));
        }
        if (actions.isEmpty()) {
            ActivableStatus oldActivable = oldState.getActivable();
            ActivableStatus newActivable = newState.getActivable();
            if (ActivableStatus.isActivation(oldActivable, newActivable)) {
                actions.add(new SuspendJmsTriggers(id));
            }
        }
        return actions;
    }

    @Override
    public Class<? extends ChangeJmsTriggerState> getActionType() {
        return ChangeJmsTriggerState.class;
    }
}
