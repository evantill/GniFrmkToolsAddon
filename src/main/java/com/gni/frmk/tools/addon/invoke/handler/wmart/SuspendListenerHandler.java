package com.gni.frmk.tools.addon.invoke.handler.wmart;

import com.gni.frmk.tools.addon.dispatcher.NoResult;
import com.gni.frmk.tools.addon.invoke.action.wmart.SuspendListener;
import com.gni.frmk.tools.addon.invoke.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.dispatcher.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
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
