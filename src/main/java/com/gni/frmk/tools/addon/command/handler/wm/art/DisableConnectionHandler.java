package com.gni.frmk.tools.addon.command.handler.wm.art;

import com.gni.frmk.tools.addon.command.action.wm.art.DisableConnection;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.command.result.NoResult;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:19
 *
 * @author: e03229
 */
public class DisableConnectionHandler extends AbstractInvokeHandler<DisableConnection, NoResult>
        implements ActionHandler<DisableConnection, NoResult, InvokeContext> {

    public DisableConnectionHandler() {
        super("wm.art.admin.connection:disableConnection");
    }

    @Override
    public Class<DisableConnection> getActionType() {
        return DisableConnection.class;
    }

    @Override
    protected NoResult parseOutput(DisableConnection action, IData output) {
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
