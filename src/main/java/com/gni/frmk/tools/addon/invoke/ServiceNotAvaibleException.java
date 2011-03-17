package com.gni.frmk.tools.addon.invoke;

import com.wm.data.*;
import com.wm.lang.ns.NSName;

/**
* Created by IntelliJ IDEA.
* Date: 17/03/11
* Time: 13:55
*
* @author: e03229
*/
public class ServiceNotAvaibleException extends ServiceInvokeException {
    public ServiceNotAvaibleException(InvokeContext ctx, NSName service, IData input, Throwable cause) {
        super(ctx, service, input, cause);
    }
}
