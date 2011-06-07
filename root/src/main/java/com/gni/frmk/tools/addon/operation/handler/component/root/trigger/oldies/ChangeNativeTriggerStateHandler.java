package com.gni.frmk.tools.addon.operation.handler.component.root.trigger.oldies;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.root.TemporaryStatus;
import com.gni.frmk.tools.addon.operation.action.component.root.trigger.ChangeNativeTriggerState;
import com.gni.frmk.tools.addon.operation.action.component.root.trigger.SuspendTriggers;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.handler.component.oldies.ChangeComponentStateHandler;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 11:59
 *
 * @author: e03229
 */
public class ChangeNativeTriggerStateHandler
        extends ChangeComponentStateHandler<ChangeNativeTriggerState, StringId, NativeTriggerState, InvokeContext> {


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
    protected List<Action<?>> defineActions(NativeTriggerState oldState, NativeTriggerState newState, StringId id) {
        //TODO gestion du enabled ?
        //EnableState enabled = newState.getEnabled();

        List<Action<?>> actions = Lists.newArrayList();
        TemporaryStatus retrievalTemporary = newState.getRetrievalState().getTemporary();
        TemporaryStatus processingTemporary = newState.getProcessingState().getTemporary();
        ActivableStatus retrievalActivated = newState.getRetrievalState().getActivable();
        ActivableStatus processingActivated = newState.getProcessingState().getActivable();
        if (retrievalTemporary == processingTemporary) {
            actions.add(SuspendTriggers.builder()
                                       .addTriggerName(id.getValue())
                                       .suspendProcessing(processingActivated.isNotActive())
                                       .suspendRetrieval(retrievalActivated.isNotActive())
                                       .persistChange(retrievalTemporary.isPermanent())
                                       .build());
        } else {
            if (retrievalTemporary == TemporaryStatus.TEMPORARY) {
                actions.add(SuspendTriggers.builder()
                                           .addTriggerName(id.getValue())
                                           .suspendProcessing(processingActivated.isNotActive())
                                           .suspendRetrieval(oldState.getRetrievalState().getActivable().isNotActive())
                                           .persistChange(true)
                                           .build());
                actions.add(SuspendTriggers.builder()
                                           .addTriggerName(id.getValue())
                                           .suspendProcessing(processingActivated.isNotActive())
                                           .suspendRetrieval(retrievalActivated.isNotActive())
                                           .persistChange(false)
                                           .build());

            }
            if (processingTemporary == TemporaryStatus.TEMPORARY) {
                actions.add(SuspendTriggers.builder()
                                           .addTriggerName(id.getValue())
                                           .suspendProcessing(oldState.getProcessingState()
                                                                      .getActivable()
                                                                      .isNotActive())
                                           .suspendRetrieval(retrievalActivated.isNotActive())
                                           .persistChange(true)
                                           .build());
                actions.add(SuspendTriggers.builder()
                                           .addTriggerName(id.getValue())
                                           .suspendProcessing(processingActivated.isNotActive())
                                           .suspendRetrieval(retrievalActivated.isNotActive())
                                           .persistChange(false)
                                           .build());
            }
        }
        return actions;
    }

    @Override
    public Class<? extends ChangeNativeTriggerState> getActionType() {
        return ChangeNativeTriggerState.class;
    }
}
