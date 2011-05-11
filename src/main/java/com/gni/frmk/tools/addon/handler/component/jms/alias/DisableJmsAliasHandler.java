package com.gni.frmk.tools.addon.handler.component.jms.alias;

import com.gni.frmk.tools.addon.action.component.jms.alias.DisableJmsAlias;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.result.NoResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.api.action.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 17:01
 *
 * @author: e03229
 */
public class DisableJmsAliasHandler
        extends AbstractInvokeHandler<DisableJmsAlias, NoResult>
        implements ActionHandler<DisableJmsAlias, NoResult, InvokeContext> {

    public DisableJmsAliasHandler() {
        super("wm.server.jms:disableConnectionAlias");
    }

    @Override
    protected IData prepareInput(DisableJmsAlias in) {
        return IDataFactory.create(new Object[][]{
                {"aliasName",
                 in.getAliasName().getValue()}
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
