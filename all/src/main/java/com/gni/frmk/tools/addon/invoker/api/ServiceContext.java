package com.gni.frmk.tools.addon.invoker.api;

import com.wm.data.*;
import com.wm.lang.ns.NSName;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 09:59
 *
 * @author: e03229
 */
public interface ServiceContext {
    IData invoke(NSName serviceName, IData in) throws ServiceContextException;

    void dispose();
}
