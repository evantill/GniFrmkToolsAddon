package com.gni.frmk.tools.addon.invoker.context;

import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceContextException;
import com.wm.data.*;
import com.wm.lang.ns.NSName;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 12:22
 *
 * @author: e03229
 */
public enum LocalContext implements ServiceContext {
    singleton;

    @Override
    public IData invoke(NSName serviceName, IData in) throws ServiceContextException {
        try {
            return com.wm.app.b2b.server.Service.doInvoke(serviceName, in);
        } catch (Exception cause) {
            throw new ServiceContextException(this, cause);
        }
    }

    @Override
    public void dispose() {
        //noop
    }
}
