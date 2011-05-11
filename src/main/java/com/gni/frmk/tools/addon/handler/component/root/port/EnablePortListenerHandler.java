package com.gni.frmk.tools.addon.handler.component.root.port;

import com.gni.frmk.tools.addon.action.component.root.port.EnablePortListener;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.result.NoResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.api.action.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 10:03
 *
 * @author: e03229
 */
public class EnablePortListenerHandler
        extends AbstractInvokeHandler<EnablePortListener, NoResult>
        implements ActionHandler<EnablePortListener, NoResult, InvokeContext> {

    public EnablePortListenerHandler() {
        super("wm.server.net.listeners:enableListener");
    }


    @Override
    protected IData prepareInput(EnablePortListener in) {
        return IDataFactory.create(new Object[][]{
                {"listenerKey",
                 in.getId().getId()},
                {"pkg",
                 in.getId().getPackageName()}
        });
    }

    @Override
    protected NoResult parseOutput(EnablePortListener action, IData output) {
        return NoResult.newInstance();
    }

    @Override
    public Class<EnablePortListener> getActionType() {
        return EnablePortListener.class;
    }
}
