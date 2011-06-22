package com.gni.frmk.tools.addon.operation.handler.component.root.trigger;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.root.NativeTriggerInfo;
import com.gni.frmk.tools.addon.invoker.io.root.NativeTriggerInfo.NativeTriggerInfoState;
import com.gni.frmk.tools.addon.invoker.service.root.GetTriggerReport;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.root.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.root.NativeTriggerType;
import com.gni.frmk.tools.addon.model.component.root.TemporaryActivableState;
import com.gni.frmk.tools.addon.model.component.root.TemporaryStatus;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentStateHandler.GetComponentStateStrategy;
import com.google.common.base.Predicate;
import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class GetNativeTriggerStateStrategy
        implements GetComponentStateStrategy<NativeTriggerType, StringId, NativeTriggerState> {

    private final GetTriggerReport triggers;

    @Inject
    public GetNativeTriggerStateStrategy(GetTriggerReport triggers) {
        this.triggers = triggers;
    }

    @Override
    public NativeTriggerType getComponentType() {
        return NativeTriggerType.TYPE;
    }

    private TemporaryActivableState newTemporaryActivableState(NativeTriggerInfoState processing) {
        return TemporaryActivableState.builder()
                                      .activable(ActivableStatus.fromBoolean(processing.isActive()))
                                      .temporary(TemporaryStatus.fromBoolean(processing.isPermanent()))
                                      .validate().build();
    }

    @Override
    public NativeTriggerState getState(final StringId componentId, InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        ListValueOutput<NativeTriggerInfo> infos = triggers.invoke(NoInput.singleton, serviceContext);
        NativeTriggerInfo info = infos.getSingleValue(new Predicate<NativeTriggerInfo>() {
            @Override
            public boolean apply(NativeTriggerInfo input) {
                return input != null && componentId.getValue().equals(input.getName());
            }
        });

        EnableState enabled = EnableState.build(EnableStatus.fromBoolean(info.isExecuteEnabled()));
        TemporaryActivableState processingState = newTemporaryActivableState(info.getProcessing());
        TemporaryActivableState retreivalState = newTemporaryActivableState(info.getRetrieval());
        return NativeTriggerState.builder()
                                 .enabled(enabled)
                                 .processingState(processingState)
                                 .retrievalState(retreivalState)
                                 .validate().build();
    }
}
