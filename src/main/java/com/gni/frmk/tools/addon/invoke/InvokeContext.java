package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.dispatcher.Action;
import com.gni.frmk.tools.addon.dispatcher.ExecutionContext;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ns.Namespace;
import com.wm.data.*;
import com.wm.lang.ns.NSName;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 14:37
 *
 * @author: e03229
 */
public class InvokeContext implements ExecutionContext {
    public IData invoke(Action<?> action, NSName service, IData input) throws ServiceInvokeException {
        try {
            return Service.doInvoke(service, input);
        } catch (Exception e) {
            throw new ServiceInvokeException(this, action, service, input, e);
        }
    }

    public boolean canInvoke(NSName service) {
        return Namespace.current().nodeExists(service);
    }

}
