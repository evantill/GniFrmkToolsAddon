package com.gni.frmk.tools.addon.operation.handler.component.art.connection;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterConnectionInfo;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterTypeInput;
import com.gni.frmk.tools.addon.invoker.service.art.ListAdapterConnections;
import com.gni.frmk.tools.addon.invoker.service.art.RetrieveAdapterTypes;
import com.gni.frmk.tools.addon.model.component.art.AdapterConnectionType;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsHandler.ListComponentIdsStrategy;
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
public class ListAdapterConnectionIdsStrategy
        implements ListComponentIdsStrategy<AdapterConnectionType, AdapterId> {

    private final ListAdapterConnections connections = new ListAdapterConnections();
    private final RetrieveAdapterTypes retrieveAdapterTypes = new RetrieveAdapterTypes();


    @Override
    public AdapterConnectionType getComponentType() {
        return AdapterConnectionType.TYPE;
    }

    @Override
    public Set<AdapterId> listIds(InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        List<String> types = retrieveAdapterTypes.invoke(NoInput.singleton, serviceContext).getValues();
        Set<AdapterId> ids = Sets.newHashSet();
        for (String adapterType : types) {
            AdapterTypeInput input = AdapterTypeInput.newInstance(adapterType);
            for (AdapterConnectionInfo info : connections.invoke(input, serviceContext).getValues()) {
                AdapterId id = AdapterConnectionType.TYPE
                        .idBuilder()
                        .adapterType(adapterType)
                        .name(info.getConnectionAlias())
                        .validate()
                        .build();
                ids.add(id);
            }
        }
        return ids;
    }
}
