package com.gni.frmk.tools.addon.operation.handler.component.root.port;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.root.PortInfo;
import com.gni.frmk.tools.addon.invoker.service.root.ListListeners;
import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.model.component.root.PortType;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ListComponentIdsHandler.ListComponentIdsStrategy;
import com.google.common.collect.Sets;
import com.google.inject.Inject;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class ListPortIdsStrategy
        implements ListComponentIdsStrategy<PortType, PackageAndStringId> {

    private final ListListeners ports;

    @Inject
    public ListPortIdsStrategy(ListListeners ports) {
        this.ports = ports;
    }

    @Override
    public Set<PackageAndStringId> listIds(InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        Set<PackageAndStringId> ids = Sets.newHashSet();
        for (PortInfo info : ports.invoke(NoInput.singleton, serviceContext).getValues()) {
            PackageAndStringId id = PortType.TYPE.idBuilder()
                                                 .id(info.getKey())
                                                 .packageName(info.getPackageName())
                                                 .validate().build();
            ids.add(id);
        }
        return ids;
    }

    @Override
    public PortType getComponentType() {
        return PortType.TYPE;
    }
}
