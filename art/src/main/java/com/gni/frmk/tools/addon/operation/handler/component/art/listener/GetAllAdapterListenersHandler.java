package com.gni.frmk.tools.addon.operation.handler.component.art.listener;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener.AdapterListenerDetail;
import com.gni.frmk.tools.addon.operation.action.component.art.listener.AdapterListenerFactory;
import com.gni.frmk.tools.addon.operation.action.component.art.listener.GetAllAdapterListeners;
import com.gni.frmk.tools.addon.operation.handler.component.GetAllComponentsHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 15:57
 *
 * @author: e03229
 */
public class GetAllAdapterListenersHandler
        extends GetAllComponentsHandler<AdapterId, AdapterListenerDetail, ActivableState, AdapterListener, GetAllAdapterListeners, InvokeContext> {

    public GetAllAdapterListenersHandler() {
        super(new AdapterListenerFactory());
    }

    @Override
    public Class<? extends GetAllAdapterListeners> getActionType() {
        return GetAllAdapterListeners.class;
    }
}
