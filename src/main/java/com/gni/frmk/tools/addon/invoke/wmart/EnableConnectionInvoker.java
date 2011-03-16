package com.gni.frmk.tools.addon.invoke.wmart;

import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.NoResult;
import com.gni.frmk.tools.addon.invoke.AbstractInvoker;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionHandler;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:21
 *
 * @author: e03229
 */
 class EnableConnectionInvoker extends AbstractInvoker<EnableConnection, NoResult>
        implements ActionHandler<EnableConnection, NoResult, InvokeContext> {

    public EnableConnectionInvoker() {
        super("wm.art.admin.connection:enableConnection");
    }

    @Override
    public Class<EnableConnection> getActionType() {
        return EnableConnection.class;
    }

    @Override
    protected NoResult parseOutput(IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(EnableConnection in) {
        return IDataFactory.create(new Object[][]{
                {"connectionAlias",
                 in.getParameter()}
        });
    }
}
