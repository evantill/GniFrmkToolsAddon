package com.gni.frmk.tools.addon.command.handler.wm.art;

import com.gni.frmk.tools.addon.command.action.wm.art.EnableListener;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.command.result.NoResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 17:22
 *
 * @author: e03229
 */
public class EnableListenerHandler extends AbstractInvokeHandler<EnableListener, NoResult>
        implements ActionHandler<EnableListener, NoResult, InvokeContext> {

    public EnableListenerHandler() {
        super("wm.art.admin.listener:enableListener");
    }

    @Override
    public Class<EnableListener> getActionType() {
        return EnableListener.class;
    }

    @Override
    protected NoResult parseOutput(EnableListener action, IData output) {
        return NoResult.newInstance();
    }

    @Override
    protected IData prepareInput(EnableListener in) {
        return IDataFactory.create(new Object[][]{
                {"listenerName",
                 in.getParameter()}
        });
    }
}
