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
public class ServiceInvokeException extends InvokeException {
    private final NSName service;
    private final IData input;

    public ServiceInvokeException(InvokeContext ctx, NSName service, IData input, Throwable cause) {
        super(ctx, cause);
        this.service = service;
        this.input = input;
    }

    public NSName getService() {
        return service;
    }

    public IData getInput() {
        return input;
    }
}
