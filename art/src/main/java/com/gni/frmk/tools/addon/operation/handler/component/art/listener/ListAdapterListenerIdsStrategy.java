package com.gni.frmk.tools.addon.operation.handler.component.art.listener;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterListenerInfo;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterTypeInput;
import com.gni.frmk.tools.addon.invoker.service.art.ListAdapterListeners;
import com.gni.frmk.tools.addon.invoker.service.art.RetrieveAdapterTypes;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterListenerType;
import com.gni.frmk.tools.addon.operation.handler.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsStrategy;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class ListAdapterListenerIdsStrategy
        implements ListComponentIdsStrategy<AdapterId> {

    private final ListAdapterListeners listeners = new ListAdapterListeners();
    private final RetrieveAdapterTypes retrieveAdapterTypes = new RetrieveAdapterTypes();

    @Override
    public Set<AdapterId> listIds(InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        List<String> types = retrieveAdapterTypes.invoke(NoInput.singleton, serviceContext).getValues();
        Set<AdapterId> ids = Sets.newHashSet();
        for (String adapterType : types) {
            AdapterTypeInput input = AdapterTypeInput.newInstance(adapterType);
            for (AdapterListenerInfo info : listeners.invoke(input, serviceContext).getValues()) {
                AdapterId id = AdapterListenerType.TYPE
                        .idBuilder()
                        .adapterType(adapterType)
                        .name(info.getNotificationNodeName())
                        .validate()
                        .build();
                ids.add(id);
            }
        }
        return ids;
    }
}
