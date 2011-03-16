package com.gni.frmk.tools.addon.invoke.wmart;

import com.gni.frmk.tools.addon.invoke.AbstractInvoker;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.NoResult;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionHandler;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:22
 *
 * @author: e03229
 */
 class EnableListenerInvoker extends AbstractInvoker<EnableListener, NoResult>
        implements ActionHandler<EnableListener, NoResult, InvokeContext> {

    public EnableListenerInvoker() {
        super("wm.art.admin.listener:enableListener");
    }

    @Override
    public Class<EnableListener> getActionType() {
        return EnableListener.class;
    }

    @Override
    protected NoResult parseOutput(IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(EnableListener in) {
        return IDataFactory.create(new Object[][]{
                {"listenerName",
                 in.getParameter()}
        });
    }
}
