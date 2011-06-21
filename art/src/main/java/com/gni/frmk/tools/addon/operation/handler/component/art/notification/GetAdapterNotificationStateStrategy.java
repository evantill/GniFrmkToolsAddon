package com.gni.frmk.tools.addon.operation.handler.component.art.notification;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterPollingNotificationInfo;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterTypeInput;
import com.gni.frmk.tools.addon.invoker.service.art.ListAdapterPollingNotifications;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotificationType;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentStateHandler.GetComponentStateStrategy;
import com.google.common.base.Predicate;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class GetAdapterNotificationStateStrategy
        implements GetComponentStateStrategy<AdapterNotificationType, AdapterId, ActivableState> {

    private final ListAdapterPollingNotifications pollingNotifications;

    @Inject
    public GetAdapterNotificationStateStrategy(ListAdapterPollingNotifications pollingNotifications) {
        this.pollingNotifications = pollingNotifications;
    }

    @Override
    public AdapterNotificationType getComponentType() {
        return AdapterNotificationType.TYPE;
    }

    @Override
    public ActivableState getState(final AdapterId componentId, InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        AdapterTypeInput input = AdapterTypeInput.newInstance(componentId.getAdapterType());
        ListValueOutput<AdapterPollingNotificationInfo> infos = pollingNotifications.invoke(input, serviceContext);
        AdapterPollingNotificationInfo info = infos.getSingleValue(new Predicate<AdapterPollingNotificationInfo>() {
            @Override
            public boolean apply(AdapterPollingNotificationInfo input) {
                return input != null &&
                       componentId.getName().equals(input.getNotificationNodeName());
            }
        });
        EnableStatus enabled = EnableStatus.UNKNOWN;
        ActivableStatus activable = ActivableStatus.UNKNOWN;
        switch (info.getNotificationEnabled()) {
            case YES:
                enabled = EnableStatus.ENABLED;
                activable = ActivableStatus.ACTIVE;
                break;
            case NO:
                enabled = EnableStatus.DISABLED;
                activable = ActivableStatus.INACTIVE;
                break;
            case SUSPENDED:
                enabled = EnableStatus.ENABLED;
                activable = ActivableStatus.INACTIVE;
                break;
            case UNSCHED:
                enabled = EnableStatus.DISABLED;
                activable = ActivableStatus.ACTIVE;
                break;
        }
        return ActivableState.build(enabled, activable);
    }

}
