package com.gni.frmk.tools.addon.invoke;

import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ns.Namespace;
import com.wm.data.IData;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 13 oct. 2010
 * Time: 15:54:34
 * To change this template use File | Settings | File Templates.
 */
public class LocalServiceInvoker extends ServiceInvoker {

    public LocalServiceInvoker(ServiceInvokerBuilder builder) {
        super(builder);
    }

    @Override
    protected IData invokeHandler(IData input) throws Exception {
        return Service.doInvoke(name, input);
    }

    @Override
    public boolean exist() {
        return Namespace.current().nodeExists(name);
    }

}
