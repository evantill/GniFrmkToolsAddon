package com.gni.frmk.tools.addon.handler.component.art.listener;

import com.gni.frmk.tools.addon.action.component.art.listener.EnableListener;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.result.NoResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.api.action.ActionHandler;

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
        super("pub.art.listener:enableListener");
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
                 in.getId().getName()}
        });
    }
}
