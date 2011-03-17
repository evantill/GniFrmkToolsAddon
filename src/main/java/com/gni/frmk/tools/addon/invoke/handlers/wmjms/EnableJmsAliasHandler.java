package com.gni.frmk.tools.addon.invoke.handlers.wmjms;

import com.gni.frmk.tools.addon.invoke.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.actions.wmjms.EnableJmsAlias;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.invoke.results.NoResult;
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
