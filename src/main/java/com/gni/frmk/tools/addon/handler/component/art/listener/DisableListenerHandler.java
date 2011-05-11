package com.gni.frmk.tools.addon.handler.component.art.listener;

import com.gni.frmk.tools.addon.action.component.art.listener.DisableListener;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.result.NoResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.api.action.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:21
 *
 * @author: e03229
 */
public class DisableListenerHandler extends AbstractInvokeHandler<DisableListener, NoResult>
        implements ActionHandler<DisableListener, NoResult, InvokeContext> {

    public DisableListenerHandler() {
        super("pub.art.listener:disableListener");
    }

    @Override
    public Class<DisableListener> getActionType() {
        return DisableListener.class;
    }

    @Override
    protected NoResult parseOutput(DisableListener action, IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(DisableListener in) {
        return IDataFactory.create(new Object[][]{
                {"listenerName",
                 in.getId().getName()}
        });
    }
}
