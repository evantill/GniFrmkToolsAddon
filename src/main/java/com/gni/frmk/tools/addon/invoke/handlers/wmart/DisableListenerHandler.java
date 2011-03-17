package com.gni.frmk.tools.addon.invoke.handlers.wmart;

import com.gni.frmk.tools.addon.invoke.ActionHandler;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.results.NoResult;
import com.gni.frmk.tools.addon.invoke.actions.wmart.DisableListener;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:21
 *
 * @author: e03229
 */
public class DisableListenerHandler extends AbstractInvokeHandler<DisableListener, NoResult>
        implements ActionHandler<DisableListener, NoResult, InvokeContext> {

    public DisableListenerHandler() {
        super("wm.art.admin.listener:disableListener");
    }

    @Override
    public Class<DisableListener> getActionType() {
        return DisableListener.class;
    }

    @Override
    protected NoResult parseOutput(DisableListener action, IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(DisableListener in) {
        return IDataFactory.create(new Object[][]{
                {"listenerName",
                 in.getParameter()}
        });
    }
}
