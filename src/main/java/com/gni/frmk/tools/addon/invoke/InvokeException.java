package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.dispatcher.Action;
import com.gni.frmk.tools.addon.dispatcher.ActionException;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 13:54
 *
 * @author: e03229
 */
public class InvokeException extends ActionException {
    private final InvokeContext ctx;

    public InvokeException(InvokeContext ctx, Action<?> action, String message) {
        super(action, message);
        this.ctx = ctx;
    }

    public InvokeException(InvokeContext ctx, Action<?> action, Throwable caught) {
        super(action, caught);
        this.ctx = ctx;
    }

    public InvokeContext getContext() {
        return ctx;
    }
}
