package com.gni.frmk.tools.addon.handler.component.jms.alias;

import com.gni.frmk.tools.addon.action.component.jms.alias.EnableJmsAlias;
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
public class EnableJmsAliasHandler extends AbstractInvokeHandler<EnableJmsAlias, NoResult>
        implements ActionHandler<EnableJmsAlias, NoResult, InvokeContext> {

    public EnableJmsAliasHandler() {
        super("wm.server.jms:enableConnectionAlias");
    }

    @Override
    protected IData prepareInput(EnableJmsAlias in) {
        return IDataFactory.create(new Object[][]{
                {"aliasName", in.getAliasName().getValue()}
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
