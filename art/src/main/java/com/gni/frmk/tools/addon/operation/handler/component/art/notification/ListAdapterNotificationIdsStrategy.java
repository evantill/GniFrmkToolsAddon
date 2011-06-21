package com.gni.frmk.tools.addon.operation.handler.component.art.notification;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterPollingNotificationInfo;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterTypeInput;
import com.gni.frmk.tools.addon.invoker.service.art.ListAdapterPollingNotifications;
import com.gni.frmk.tools.addon.invoker.service.art.RetrieveAdapterTypes;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterNotificationType;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsHandler.ListComponentIdsStrategy;
import com.google.common.collect.Sets;
import com.google.inject.Inject;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class ListAdapterNotificationIdsStrategy
        implements ListComponentIdsStrategy<AdapterNotificationType, AdapterId> {

    private final ListAdapterPollingNotifications pollingNotifications;
    private final RetrieveAdapterTypes retrieveAdapterTypes;

    @Inject
    public ListAdapterNotificationIdsStrategy(ListAdapterPollingNotifications pollingNotifications, RetrieveAdapterTypes retrieveAdapterTypes) {
        this.pollingNotifications = pollingNotifications;
        this.retrieveAdapterTypes = retrieveAdapterTypes;
    }

    @Override
    public Set<AdapterId> listIds(InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        List<String> types = retrieveAdapterTypes.invoke(NoInput.singleton, serviceContext).getValues();
        Set<AdapterId> ids = Sets.newHashSet();
        for (String adapterType : types) {
            AdapterTypeInput input = AdapterTypeInput.newInstance(adapterType);
            for (AdapterPollingNotificationInfo info : pollingNotifications.invoke(input, serviceContext).getValues()) {
                AdapterId id = AdapterNotificationType.TYPE.idBuilder()
                                                           .adapterType(adapterType)
                                                           .name(info.getNotificationNodeName())
                                                           .validate().build();
                ids.add(id);
            }
        }
        return ids;
    }

    @Override
    public AdapterNotificationType getComponentType() {
        return AdapterNotificationType.TYPE;
    }
}
