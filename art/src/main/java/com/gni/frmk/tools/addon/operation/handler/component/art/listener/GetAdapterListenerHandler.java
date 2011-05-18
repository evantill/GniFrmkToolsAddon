package com.gni.frmk.tools.addon.operation.handler.component.art.listener;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.model.component.art.AdapterId;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener;
import com.gni.frmk.tools.addon.model.component.art.AdapterListener.AdapterListenerDetail;
import com.gni.frmk.tools.addon.operation.action.component.art.listener.AdapterListenerFactory;
import com.gni.frmk.tools.addon.operation.action.component.art.listener.GetAdapterListener;
import com.gni.frmk.tools.addon.operation.handler.component.GetComponentHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/05/11
 * Time: 15:56
 *
 * @author: e03229
 */
public class GetAdapterListenerHandler
        extends GetComponentHandler<AdapterId, AdapterListenerDetail, ActivableState, AdapterListener, GetAdapterListener, InvokeContext> {

    public GetAdapterListenerHandler() {
        super(new AdapterListenerFactory());
    }

    @Override
    public Class<? extends GetAdapterListener> getActionType() {
        return GetAdapterListener.class;
    }
}
