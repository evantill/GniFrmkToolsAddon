package com.gni.frmk.tools.addon.operation.handler.component.art.listener;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterComponentId;
import com.gni.frmk.tools.addon.invoker.service.art.DisableListener;
import com.gni.frmk.tools.addon.invoker.service.art.EnableListener;
import com.gni.frmk.tools.addon.invoker.service.art.ResumeListener;
import com.gni.frmk.tools.addon.invoker.service.art.SuspendListener;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterListenerType;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.ChangeComponentStateHandler.ChangeComponentStateStrategy;

/**
 * Created by IntelliJ IDEA.
 * Date: 15/06/11
 * Time: 14:19
 *
 * @author: e03229
 */
public class ChangeAdapterListenerStateStrategy
        implements ChangeComponentStateStrategy<AdapterListenerType, AdapterId, ActivableState> {

    private final EnableListener enableService = new EnableListener();
    private final DisableListener disableService = new DisableListener();
    private final ResumeListener resumeService = new ResumeListener();
    private final SuspendListener suspendService = new SuspendListener();

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
    public AdapterListenerType getComponentType() {
        return AdapterListenerType.TYPE;
    }
}
