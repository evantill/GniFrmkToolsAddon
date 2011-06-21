package com.gni.frmk.tools.addon.operation.handler.component.root.port;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.root.PortInput;
import com.gni.frmk.tools.addon.invoker.service.root.DisableListener;
import com.gni.frmk.tools.addon.invoker.service.root.EnableListener;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.PackageAndStringId;
import com.gni.frmk.tools.addon.model.component.root.PortType;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ChangeComponentStateHandler.ChangeComponentStateStrategy;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * Date: 15/06/11
 * Time: 14:19
 *
 * @author: e03229
 */
public class ChangePortStateStrategy
        implements ChangeComponentStateStrategy<PortType, PackageAndStringId, ActivableState> {

    private final DisableListener disableService;
    private final EnableListener enableService;

    @Inject
    public ChangePortStateStrategy(DisableListener disableService, EnableListener enableService) {
        this.disableService = disableService;
        this.enableService = enableService;
    }

    @Override
    public ActivableState changeState(PackageAndStringId componentId, ActivableState oldState, ActivableState newState, InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        PortInput serviceInput = PortInput.newInstance(componentId.getId(), componentId.getPackageName());
        EnableStatus oldEnabled = oldState.getEnabled();
        EnableStatus newEnabled = newState.getEnabled();
        if (EnableStatus.isDisabling(oldEnabled, newEnabled)) {
            enableService.invoke(serviceInput, serviceContext);
        } else if (EnableStatus.isEnabling(oldEnabled, newEnabled)) {
            disableService.invoke(serviceInput, serviceContext);
        }
        return newState;
    }

    @Override
    public PortType getComponentType() {
        return PortType.TYPE;
    }
}
