package com.gni.frmk.tools.addon.invoke.wmart;

import com.gni.frmk.tools.addon.invoke.NoResult;
import com.gni.frmk.tools.addon.invoke.AbstractInvoker;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:24
 *
 * @author: e03229
 */
 class SuspendListenerInvoker extends AbstractInvoker<SuspendListener, NoResult>
        implements ActionHandler<SuspendListener, NoResult, InvokeContext> {

    public SuspendListenerInvoker() {
        super("wm.art.admin.listener:suspendListener");
    }

    @Override
    public Class<SuspendListener> getActionType() {
        return SuspendListener.class;
    }

    @Override
    protected NoResult parseOutput(IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(SuspendListener in) {
        return IDataFactory.create(new Object[][]{
                {"listenerName",
                 in.getParameter()}
        });
    }
}
