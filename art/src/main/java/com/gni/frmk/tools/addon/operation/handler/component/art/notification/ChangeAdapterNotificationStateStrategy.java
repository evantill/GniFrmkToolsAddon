package com.gni.frmk.tools.addon.operation.handler.component.art.notification;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterComponentId;
import com.gni.frmk.tools.addon.invoker.service.art.DisablePollingNotification;
import com.gni.frmk.tools.addon.invoker.service.art.EnablePollingNotification;
import com.gni.frmk.tools.addon.invoker.service.art.ResumePollingNotification;
import com.gni.frmk.tools.addon.invoker.service.art.SuspendPollingNotification;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotificationType;
import com.gni.frmk.tools.addon.operation.handler.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ChangeComponentStateHandler.ChangeComponentStateStrategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 15/06/11
 * Time: 14:19
 *
 * @author: e03229
 */
public class ChangeAdapterNotificationStateStrategy
        implements ChangeComponentStateStrategy<AdapterNotificationType, AdapterId, ActivableState> {

    private final EnablePollingNotification enableService = new EnablePollingNotification();
    private final DisablePollingNotification disableService = new DisablePollingNotification();
    private final ResumePollingNotification resumeService = new ResumePollingNotification();
    private final SuspendPollingNotification suspendService = new SuspendPollingNotification();

    @Override
    public ActivableState changeState(AdapterId componentId, ActivableState oldState, ActivableState newState, InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        AdapterComponentId serviceInput = AdapterComponentId.newInstance(componentId.getAdapterType(), componentId.getName());
        EnableStatus oldEnabled = oldState.getEnabled();
        EnableStatus newEnabled = newState.getEnabled();
        if (EnableStatus.isEnabling(oldEnabled, newEnabled)) {
            enableService.invoke(serviceInput, serviceContext);
        }
        if (EnableStatus.isDisabling(oldEnabled, newEnabled)) {

            disableService.invoke(serviceInput, serviceContext);
        }
        ActivableStatus oldActivated = oldState.getActivable();
        ActivableStatus newActivated = newState.getActivable();
        if (ActivableStatus.isActivation(oldActivated, newActivated)) {
            resumeService.invoke(serviceInput, serviceContext);
        }
        if (ActivableStatus.isDesactivation(oldActivated, newActivated)) {
            suspendService.invoke(serviceInput, serviceContext);
        }
        return newState;
    }

    @Override
    public AdapterNotificationType getComponentType() {
        return AdapterNotificationType.TYPE;
    }
}
