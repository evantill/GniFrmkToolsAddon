package com.gni.frmk.tools.addon.command.dispatch.wm.invoke.context;

import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.ServiceInvokeException;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ns.Namespace;
import com.wm.data.*;
import com.wm.lang.ns.NSName;

/**
 * Created by IntelliJ IDEA.
 * Date: 04/04/11
 * Time: 18:21
 *
 * @author: e03229
 */
public class InvokeContextLocal implements InvokeContext {

    @Override
    public IData invoke(Action<?> action, NSName service, IData input) throws ServiceInvokeException {
        try {
            return Service.doInvoke(service, input);
        } catch (Exception e) {
            throw new ServiceInvokeException(this, action, service, input, e);
        }
    }

    @Override
    public boolean canInvoke(NSName service) {
        return Namespace.current().nodeExists(service);
    }
}
