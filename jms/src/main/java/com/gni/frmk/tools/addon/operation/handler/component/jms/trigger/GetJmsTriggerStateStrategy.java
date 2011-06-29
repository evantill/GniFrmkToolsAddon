package com.gni.frmk.tools.addon.operation.handler.component.jms.trigger;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.api.ServiceNotFoundException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.jms.JmsTriggerInfo;
import com.gni.frmk.tools.addon.invoker.service.jms.GetJmsTriggerReport;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.StringId;
import com.gni.frmk.tools.addon.model.component.jms.JmsTriggerType;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentStateHandler.GetComponentStateStrategy;
import com.google.common.base.Predicate;

import javax.inject.Inject;
import java.util.NoSuchElementException;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class GetJmsTriggerStateStrategy
        implements GetComponentStateStrategy<JmsTriggerType, StringId, ActivableState> {

    private final GetJmsTriggerReport triggers;

    @Inject
    public GetJmsTriggerStateStrategy(GetJmsTriggerReport triggers) {
        this.triggers = triggers;
    }

    @Override
    public JmsTriggerType getComponentType() {
        return JmsTriggerType.TYPE;
    }

    @Override
    public ActivableState getStateOrUnknown(StringId componentId, InvokeContext context) throws ServiceException {
        try {
            return getState(componentId, context);
        } catch (NoSuchElementException unknown) {
            return ActivableState.UNKNOWN;
        } catch (ServiceNotFoundException unknown) {
            return ActivableState.UNKNOWN;
        }
    }

    @Override
    public ActivableState getState(final StringId componentId, InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        ListValueOutput<JmsTriggerInfo> infos = triggers.invoke(NoInput.singleton, serviceContext);
        JmsTriggerInfo info = infos.getSingleValue(new Predicate<JmsTriggerInfo>() {
            @Override
            public boolean apply(JmsTriggerInfo input) {
                return input != null && componentId.getValue().equals(input.getNodeName());
            }
        });
        EnableStatus enabled = EnableStatus.fromBoolean(info.isEnabled());
        ActivableStatus activable = ActivableStatus.fromBoolean(info.isRunning());
        return ActivableState.build(enabled, activable);
    }

}
