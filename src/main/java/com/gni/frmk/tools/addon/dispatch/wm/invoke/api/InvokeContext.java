package com.gni.frmk.tools.addon.dispatch.wm.invoke.api;

import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.ExecutionContext;
import com.wm.data.*;
import com.wm.lang.ns.NSName;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 14:37
 *
 * @author: e03229
 */
public interface InvokeContext extends ExecutionContext {
    IData invoke(Action<?> action, NSName service, IData input) throws ServiceInvokeException;

    boolean canInvoke(NSName service);


}
