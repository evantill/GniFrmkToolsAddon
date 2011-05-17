package com.gni.frmk.tools.addon.operation.handler.component.art.connection;

import com.gni.frmk.tools.addon.operation.action.component.art.connection.EnableConnection;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.result.NoResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;

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
    protected NoResult parseOutput(EnableConnection action, IData output) throws ParseOutputException {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(EnableConnection action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"connectionAlias",
                 action.getId().getName()}
        });
    }
}