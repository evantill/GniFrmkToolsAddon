package com.gni.frmk.tools.addon.operation.handler.component.art.connection;

import com.gni.frmk.tools.addon.operation.action.component.art.connection.DisableConnection;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.result.NoResult;
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
        super("pub.art.connection:disableConnection");
    }

    @Override
    public Class<DisableConnection> getActionType() {
        return DisableConnection.class;
    }

    @Override
    protected NoResult parseOutput(DisableConnection action, IData output) throws ParseOutputException {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(DisableConnection action) throws ParseInputException {
        return IDataFactory.create(new Object[][]{
                {"connectionAlias",
                 action.getId().getName()}
        });
    }
}
