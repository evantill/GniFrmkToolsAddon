package com.gni.frmk.tools.addon.operation.handler;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.Result;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInvokeException;
import com.wm.lang.ns.NSName;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/03/11
 * Time: 13:04
 *
 * @author: e03229
 */
public interface InvokeHandler<A extends Action<R>, R extends Result>
        extends ActionHandler<A, R, InvokeContext> {

    @Override
    R execute(A action, InvokeContext context) throws ServiceInvokeException;

    NSName getService();
}
