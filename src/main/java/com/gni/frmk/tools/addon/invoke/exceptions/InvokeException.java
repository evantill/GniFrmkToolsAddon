package com.gni.frmk.tools.addon.invoke.exceptions;

import com.gni.frmk.tools.addon.invoke.InvokeContext;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 13:54
 *
 * @author: e03229
 */
public class InvokeException extends DispatchException {
    private final InvokeContext ctx;

    public InvokeException(InvokeContext ctx, Throwable cause) {
        super(cause);
        this.ctx = ctx;
    }

    public InvokeContext getContext() {
        return ctx;
    }
}
