package com.gni.frmk.tools.addon.operation.handler.component.art.listener;

import com.gni.frmk.tools.addon.operation.action.component.art.listener.SuspendListener;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.result.NoResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:24
 *
 * @author: e03229
 */
public class SuspendListenerHandler extends AbstractInvokeHandler<SuspendListener, NoResult>
        implements ActionHandler<SuspendListener, NoResult, InvokeContext> {

    public SuspendListenerHandler() {
        super("pub.art.listener:suspendListener");
    }

    @Override
    public Class<SuspendListener> getActionType() {
        return SuspendListener.class;
    }

    @Override
    protected NoResult parseOutput(SuspendListener action, IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(SuspendListener in) {
        return IDataFactory.create(new Object[][]{
                {"listenerName",
                 in.getId().getName()}
        });
    }
}
