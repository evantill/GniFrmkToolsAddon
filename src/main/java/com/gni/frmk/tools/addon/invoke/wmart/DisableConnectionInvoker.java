package com.gni.frmk.tools.addon.invoke.wmart;

import com.gni.frmk.tools.addon.invoke.AbstractInvoker;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.NoResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:19
 *
 * @author: e03229
 */
 class DisableConnectionInvoker extends AbstractInvoker<DisableConnection, NoResult>
        implements ActionHandler<DisableConnection, NoResult, InvokeContext> {

    public DisableConnectionInvoker() {
        super("wm.art.admin.connection:disableConnection");
    }

    @Override
    public Class<DisableConnection> getActionType() {
        return DisableConnection.class;
    }

    @Override
    protected NoResult parseOutput(IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(DisableConnection in) {
        return IDataFactory.create(new Object[][]{
                {"connectionAlias",
                 in.getParameter()}
        });
    }
}
