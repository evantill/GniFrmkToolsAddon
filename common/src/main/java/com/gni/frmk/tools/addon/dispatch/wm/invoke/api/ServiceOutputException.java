package com.gni.frmk.tools.addon.dispatch.wm.invoke.api;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.wm.data.*;
import com.wm.lang.ns.NSName;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:32
 *
 * @author: e03229
 */
public class ServiceOutputException extends ServiceInvokeException {
    private final ParseOutputException parseException;

    public ServiceOutputException(InvokeContext ctx, Action<?> action, NSName service, ParseOutputException caught) {
        super(ctx, action, service, caught);
        this.parseException = caught;
    }

    public ParseOutputException getParseException() {
        return parseException;
    }

    public static class ParseOutputException extends Exception{
        private final IData output;

        public ParseOutputException(String message, IData output) {
            super(message);
            this.output = output;
        }

        public ParseOutputException(String message, Throwable cause, IData output) {
            super(message, cause);
            this.output = output;
        }

        public IData getOutput() {
            return output;
        }
    }
}