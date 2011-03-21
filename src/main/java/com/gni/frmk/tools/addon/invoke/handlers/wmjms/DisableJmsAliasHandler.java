package com.gni.frmk.tools.addon.invoke.handlers.wmjms;

import com.gni.frmk.tools.addon.dispatcher.ActionHandler;
import com.gni.frmk.tools.addon.dispatcher.NoResult;
import com.gni.frmk.tools.addon.invoke.actions.wmjms.DisableJmsAlias;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractInvokeHandler;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 17:01
 *
 * @author: e03229
 */
public class DisableJmsAliasHandler extends AbstractInvokeHandler<DisableJmsAlias, NoResult>
        implements ActionHandler<DisableJmsAlias, NoResult, InvokeContext> {

    public DisableJmsAliasHandler() {
        super("wm.server.jms:disableConnectionAlias");
    }

    @Override
    protected IData prepareInput(DisableJmsAlias in) {
        return IDataFactory.create(new Object[][]{
                {"aliasName",
                 in.getAliasName()}
        });
    }

    @Override
    protected NoResult parseOutput(DisableJmsAlias action, IData output) {
        return NoResult.newInstance();
    }

    @Override
    public Class<DisableJmsAlias> getActionType() {
        return DisableJmsAlias.class;
    }
}
