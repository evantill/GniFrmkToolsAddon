package com.gni.frmk.tools.addon.command.handler.wm.root;

import com.gni.frmk.tools.addon.command.action.wm.root.DisablePortListener;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.command.result.NoResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 10:03
 *
 * @author: e03229
 */
public class DisablePortListenerHandler extends AbstractInvokeHandler<DisablePortListener, NoResult>
        implements ActionHandler<DisablePortListener, NoResult, InvokeContext> {
    public DisablePortListenerHandler() {
        super("wm.server.net.listeners:disableListener");
    }

    @Override
    protected IData prepareInput(DisablePortListener in) {
        return IDataFactory.create(new Object[][]{
                {"listenerKey",
                 in.getParameter()},
                {"pkg",
                 in.getPackageName()}
        });
    }

    @Override
    protected NoResult parseOutput(DisablePortListener action, IData output) {
        return NoResult.newInstance();
    }

    @Override
    public Class<DisablePortListener> getActionType() {
        return DisablePortListener.class;
    }
}
