package com.gni.frmk.tools.addon.handler.wm.art.connection;

import com.gni.frmk.tools.addon.action.wm.art.connection.EnableConnection;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.result.NoResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:21
 *
 * @author: e03229
 */
public class EnableConnectionHandler extends AbstractInvokeHandler<EnableConnection, NoResult>
        implements ActionHandler<EnableConnection, NoResult, InvokeContext> {

    public EnableConnectionHandler() {
        super("pub.art.connection:enableConnection");
    }

    @Override
    public Class<EnableConnection> getActionType() {
        return EnableConnection.class;
    }

    @Override
    protected NoResult parseOutput(EnableConnection action, IData output) {
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
