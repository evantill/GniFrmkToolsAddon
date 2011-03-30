package com.gni.frmk.tools.addon.invoke.handler.wmart;

import com.gni.frmk.tools.addon.dispatcher.ActionHandler;
import com.gni.frmk.tools.addon.dispatcher.NoResult;
import com.gni.frmk.tools.addon.invoke.action.wmart.EnableListener;
import com.gni.frmk.tools.addon.invoke.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
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
