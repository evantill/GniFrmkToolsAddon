package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.dispatcher.Action;
import com.gni.frmk.tools.addon.dispatcher.ActionException;
import com.gni.frmk.tools.addon.dispatcher.ActionHandler;
import com.gni.frmk.tools.addon.dispatcher.Dispatch;
import com.gni.frmk.tools.addon.dispatcher.Result;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 14:31
 *
 * @author: e03229
 */
abstract class InvokeDispatcher implements Dispatch {

    protected abstract InvokeServiceRegistry getHandlerRegistry();

    protected abstract InvokeContext getInvokeContext();

    @Override
    public <A extends Action<R>, R extends Result> R execute(A action) throws ActionException {
        ActionHandler<A, R, InvokeContext> handler = getHandlerRegistry().findInvoker(action);
        return handler.execute(action, getInvokeContext());
    }

    public <H extends ActionHandler<?,?,? extends InvokeContext>> void registerHandler(H handler) {
        getHandlerRegistry().addHandler(handler);
    }
}
