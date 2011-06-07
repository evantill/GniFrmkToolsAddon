package com.gni.frmk.tools.addon.operation.handler.component.art.listener.oldies;

import com.gni.frmk.tools.addon.operation.action.component.art.listener.oldies.DisableAdapterListener;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.result.NoResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:21
 *
 * @author: e03229
 */
public class DisableAdapterListenerHandler extends AbstractInvokeHandler<DisableAdapterListener, NoResult>
        implements ActionHandler<DisableAdapterListener, NoResult, InvokeContext> {

    public DisableAdapterListenerHandler() {
        super("pub.art.listener:disableListener");
    }

    @Override
    public Class<DisableAdapterListener> getActionType() {
        return DisableAdapterListener.class;
    }

    @Override
    protected NoResult parseOutput(DisableAdapterListener action, IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(DisableAdapterListener in) {
        return IDataFactory.create(new Object[][]{
                {"listenerName",
                 in.getId().getName()}
        });
    }
}
