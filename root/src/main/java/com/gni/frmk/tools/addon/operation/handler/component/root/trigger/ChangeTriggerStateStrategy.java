package com.gni.frmk.tools.addon.operation.handler.component.root.trigger;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.root.NativeTriggerInput;
import com.gni.frmk.tools.addon.invoker.service.root.SuspendTrigger;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.root.NativeTriggerType;
import com.gni.frmk.tools.addon.model.component.root.TemporaryStatus;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ChangeComponentStateHandler.ChangeComponentStateStrategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 15/06/11
 * Time: 14:19
 *
 * @author: e03229
 */
public class ChangeTriggerStateStrategy
        implements ChangeComponentStateStrategy<NativeTriggerType, StringId, NativeTriggerState> {

    private final SuspendTrigger triggerService = new SuspendTrigger();

    /**
     * Le changement de statut est le suivant :
     * <code>
     * <ul>
     * <li>Enable/Permanent -> Enable/Permanent</li>
     * <li>Disable/Permanent -> Disable/Permanent</li>
     * <li>Enable/Temporary -> Enable/Temporary</li>
     * <li>Disable/Temporary -> Disable/Permanent</li>
     * </ul>
     * </code>
     */
    @Override
    public NativeTriggerState changeState(StringId componentId, NativeTriggerState oldState, NativeTriggerState newState, InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        //TODO gestion du enabled ?
        //EnableState enabled = newState.getEnabled();
        TemporaryStatus retrievalTemporary = newState.getRetrievalState().getTemporary();
        TemporaryStatus processingTemporary = newState.getProcessingState().getTemporary();
        ActivableStatus retrievalActivated = newState.getRetrievalState().getActivable();
        ActivableStatus processingActivated = newState.getProcessingState().getActivable();
        if (retrievalTemporary == processingTemporary) {
            NativeTriggerInput serviceInput = NativeTriggerInput.builder()
                                                                .triggerNames(componentId.getValue())
                                                                .processingSuspend(processingActivated.isNotActive())
                                                                .retrievalSuspend(retrievalActivated.isNotActive())
                                                                .persistChange(retrievalTemporary.isPermanent())
                                                                .build();
            triggerService.invoke(serviceInput, serviceContext);
        } else if (retrievalTemporary == TemporaryStatus.TEMPORARY) {
            NativeTriggerInput permanentServiceInput = NativeTriggerInput.builder()
                                                                         .triggerNames(componentId.getValue())
                                                                         .processingSuspend(processingActivated.isNotActive())
                                                                         .retrievalSuspend(oldState.getRetrievalState()
                                                                                                   .getActivable()
                                                                                                   .isNotActive())
                                                                         .persistChange(true)
                                                                         .build();
            triggerService.invoke(permanentServiceInput, serviceContext);
            NativeTriggerInput temporaryServiceInput = NativeTriggerInput.builder()
                                                                         .triggerNames(componentId.getValue())
                                                                         .processingSuspend(processingActivated.isNotActive())
                                                                         .retrievalSuspend(retrievalActivated.isNotActive())
                                                                         .persistChange(false)
                                                                         .build();
            triggerService.invoke(temporaryServiceInput, serviceContext);
        } else if (processingTemporary == TemporaryStatus.TEMPORARY) {
            NativeTriggerInput permanentServiceInput = NativeTriggerInput.builder()
                                                                         .triggerNames(componentId.getValue())
                                                                         .processingSuspend(oldState.getProcessingState()
                                                                                                    .getActivable()
                                                                                                    .isNotActive())
                                                                         .retrievalSuspend(retrievalActivated.isNotActive())
                                                                         .persistChange(true)
                                                                         .build();
            triggerService.invoke(permanentServiceInput, serviceContext);
            NativeTriggerInput temporaryServiceInput = NativeTriggerInput.builder()
                                                                         .triggerNames(componentId.getValue())
                                                                         .processingSuspend(processingActivated.isNotActive())
                                                                         .retrievalSuspend(retrievalActivated.isNotActive())
                                                                         .persistChange(false)
                                                                         .build();
            triggerService.invoke(temporaryServiceInput, serviceContext);
        }
        return newState;
    }

    @Override
    public NativeTriggerType getComponentType() {
        return NativeTriggerType.TYPE;
    }
}
