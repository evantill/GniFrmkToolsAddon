package com.gni.frmk.tools.addon.command.handler.wm.jms;

import com.gni.frmk.tools.addon.command.action.wm.jms.EnableJmsAlias;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.command.result.NoResult;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 17:01
 *
 * @author: e03229
 */
public class EnableJmsAliasHandler extends AbstractInvokeHandler<EnableJmsAlias, NoResult>
        implements ActionHandler<EnableJmsAlias, NoResult, InvokeContext> {

    public EnableJmsAliasHandler() {
        super("wm.server.jms:enableConnectionAlias");
    }

    @Override
    protected IData prepareInput(EnableJmsAlias in) {
        return IDataFactory.create(new Object[][]{
                {"aliasName",
                 in.getAliasName()}
        });
    }

    @Override
    protected NoResult parseOutput(EnableJmsAlias action, IData output) {
        return NoResult.newInstance();
    }

    @Override
    public Class<EnableJmsAlias> getActionType() {
        return EnableJmsAlias.class;
    }
}
