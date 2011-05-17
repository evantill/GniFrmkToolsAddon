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
public class ServiceInputException extends ServiceInvokeException {
    private final ParseInputException parseException;

    public ServiceInputException(InvokeContext ctx, Action<?> action, NSName service, ParseInputException caught) {
        super(ctx, action, service, caught);
        this.parseException = caught;
    }

    public ParseInputException getParseException() {
        return parseException;
    }

    public static class ParseInputException extends Exception {
        private final IData input;

        public ParseInputException(String message, IData input) {
            super(message);
            this.input = input;
        }

        public ParseInputException(String message, Throwable cause, IData input) {
            super(message, cause);
            this.input = input;
        }

        public ParseInputException(Throwable cause, IData input) {
            super(cause);
            this.input = input;
        }

        public IData getInput() {
            return input;
        }
    }
}