package com.gni.frmk.tools.addon.operation.handler.component.art.listener;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.api.ServiceNotFoundException;
import com.gni.frmk.tools.addon.invoker.io.ListValueOutput;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterListenerInfo;
import com.gni.frmk.tools.addon.invoker.io.art.AdapterTypeInput;
import com.gni.frmk.tools.addon.invoker.service.art.ListAdapterListeners;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterListenerType;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentStateHandler.GetComponentStateStrategy;
import com.google.common.base.Predicate;

import javax.inject.Inject;
import java.util.NoSuchElementException;

import static com.gni.frmk.tools.addon.model.component.EnableState.UNKNOWN;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 18:47
 *
 * @author: e03229
 */
public class GetAdapterListenerStateStrategy
        implements GetComponentStateStrategy<AdapterListenerType, AdapterId, ActivableState> {

    private final ListAdapterListeners listeners;

    @Inject
    public GetAdapterListenerStateStrategy(ListAdapterListeners listeners) {
        this.listeners = listeners;
    }

    @Override
    public AdapterListenerType getComponentType() {
        return AdapterListenerType.TYPE;
    }

    @Override
    public ActivableState getStateOrUnknown(AdapterId componentId, InvokeContext context) throws ServiceException {
        try {
            return getState(componentId, context);
        } catch (NoSuchElementException unknown) {
            return ActivableState.UNKNOWN;
        } catch (ServiceNotFoundException unknown){
            return ActivableState.UNKNOWN;
        }
    }

    @Override
    public ActivableState getState(final AdapterId componentId, InvokeContext context) throws ServiceException {
        ServiceContext serviceContext = context.getServiceContext();
        AdapterTypeInput input = AdapterTypeInput.newInstance(componentId.getAdapterType());
        ListValueOutput<AdapterListenerInfo> infos = listeners.invoke(input, serviceContext);
        AdapterListenerInfo info = infos.getSingleValue(new Predicate<AdapterListenerInfo>() {
            @Override
            public boolean apply(AdapterListenerInfo input) {
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
