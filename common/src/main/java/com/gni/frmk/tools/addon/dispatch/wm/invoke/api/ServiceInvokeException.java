package com.gni.frmk.tools.addon.dispatch.wm.invoke.api;

import com.gni.frmk.tools.addon.operation.api.Action;
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

    public ServiceInvokeException(InvokeContext ctx, Action<?> action, NSName service, String message) {
        super(ctx, action, message);
        this.service = service;
    }

    public ServiceInvokeException(InvokeContext ctx, Action<?> action, NSName service, Throwable caught) {
        super(ctx, action, caught);
        this.service = service;
    }

    public NSName getService() {
        return service;
    }
}
