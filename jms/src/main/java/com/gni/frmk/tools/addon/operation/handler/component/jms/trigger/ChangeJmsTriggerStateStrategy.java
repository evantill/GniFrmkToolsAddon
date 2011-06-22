package com.gni.frmk.tools.addon.operation.handler.component.jms.trigger;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.jms.JmsTriggerInput;
import com.gni.frmk.tools.addon.invoker.service.jms.DisableJMSTriggers;
import com.gni.frmk.tools.addon.invoker.service.jms.EnableJMSTriggers;
import com.gni.frmk.tools.addon.invoker.service.jms.SuspendJMSTriggers;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsTriggerType;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ChangeComponentStateHandler.ChangeComponentStateStrategy;
import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * Date: 15/06/11
 * Time: 14:19
 *
 * @author: e03229
 */
public class ChangeJmsTriggerStateStrategy
        implements ChangeComponentStateStrategy<JmsTriggerType, StringId, ActivableState> {

    private final DisableJMSTriggers disableTriggers;
    private final EnableJMSTriggers enableTriggers;
    private final SuspendJMSTriggers suspendTriggers;

    @Inject
    public ChangeJmsTriggerStateStrategy(DisableJMSTriggers disableTriggers, EnableJMSTriggers enableTriggers, SuspendJMSTriggers suspendTriggers) {
        this.disableTriggers = disableTriggers;
        this.enableTriggers = enableTriggers;
        this.suspendTriggers = suspendTriggers;
    }

    @Override
    public ActivableState changeState(StringId componentId, ActivableState oldState, ActivableState newState, InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        JmsTriggerInput serviceInput = JmsTriggerInput.builder().triggerNames(componentId.getValue()).build();
        EnableStatus oldEnable = oldState.getEnabled();
        EnableStatus newEnable = newState.getEnabled();
        if (EnableStatus.isEnabling(oldEnable, newEnable)) {
            enableTriggers.invoke(serviceInput, serviceContext);
        } else if (EnableStatus.isDisabling(oldEnable, newEnable)) {
            disableTriggers.invoke(serviceInput, serviceContext);
        } else {
            ActivableStatus oldActivable = oldState.getActivable();
            ActivableStatus newActivable = newState.getActivable();
            if (ActivableStatus.isActivation(oldActivable, newActivable)) {
                suspendTriggers.invoke(serviceInput, serviceContext);
            }
        }
        return newState;
    }

    @Override
    public JmsTriggerType getComponentType() {
        return JmsTriggerType.TYPE;
    }
}
