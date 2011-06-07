package com.gni.frmk.tools.addon.operation.handler.component.jms.alias.oldies;

import com.gni.frmk.tools.addon.operation.action.component.jms.alias.EnableJmsAlias;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.result.NoResult;
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
                {"aliasName", in.getId().getValue()}
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