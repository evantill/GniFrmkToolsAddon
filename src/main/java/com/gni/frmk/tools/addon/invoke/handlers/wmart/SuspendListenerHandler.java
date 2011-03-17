package com.gni.frmk.tools.addon.invoke.handlers.wmart;

import com.gni.frmk.tools.addon.invoke.handlers.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.invoke.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.results.NoResult;
import com.gni.frmk.tools.addon.invoke.actions.wmart.SuspendListener;
import com.wm.data.*;

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
        super("wm.art.admin.listener:suspendListener");
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
                 in.getParameter()}
        });
    }
}
