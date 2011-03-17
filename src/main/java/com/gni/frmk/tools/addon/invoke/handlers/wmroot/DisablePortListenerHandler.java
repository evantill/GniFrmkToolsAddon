package com.gni.frmk.tools.addon.invoke.handlers.wmroot;

import com.gni.frmk.tools.addon.invoke.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.DisablePortListener;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractHandler;
import com.gni.frmk.tools.addon.invoke.results.NoResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 10:03
 *
 * @author: e03229
 */
public class DisablePortListenerHandler extends AbstractHandler<DisablePortListener, NoResult>
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
    protected NoResult parseOutput(IData output) {
        return NoResult.newInstance();
    }

    @Override
    public Class<DisablePortListener> getActionType() {
        return DisablePortListener.class;
    }
}
