package com.gni.frmk.tools.addon.operation.handler.component.root.port;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.root.PortInfo;
import com.gni.frmk.tools.addon.invoker.service.root.ListListeners;
import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.model.component.root.PortDetail;
import com.gni.frmk.tools.addon.model.component.root.PortType;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentDetailHandler.GetComponentDetailStrategy;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class GetPortDetailStrategy
        implements GetComponentDetailStrategy<PortType, PackageAndStringId, PortDetail> {

    private final ListListeners ports = new ListListeners();

    @Override
    public PortType getComponentType() {
        return PortType.TYPE;
    }

    @Override
    public PortDetail getDetail(final PackageAndStringId componentId, InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        ListValueOutput<PortInfo> infos = ports.invoke(NoInput.singleton, serviceContext);
        PortInfo info = infos.getSingleValue(new Predicate<PortInfo>() {
            @Override
            public boolean apply(PortInfo input) {
                return input != null
                       && Objects.equal(componentId.getPackageName(), input.getPackageName())
                       && Objects.equal(componentId.getId(), input.getKey());
            }
        });
        return PortDetail.newInstance(info.isPrimary());
    }

}
