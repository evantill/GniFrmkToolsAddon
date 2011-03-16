package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionException;
import com.gni.frmk.tools.addon.invoke.ActionPattern.ExecutionContext;
import com.wm.app.b2b.server.Service;
import com.wm.data.*;
import com.wm.lang.ns.NSName;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 14:37
 *
 * @author: e03229
 */
public class InvokeContext implements ExecutionContext {
    public IData invoke(NSName service, IData input) throws InvokeException {
        try {
            return Service.doInvoke(service, input);
        } catch (Exception e) {
            throw new ServiceInvokeException(this, service, input, e);
        }
    }

    public class InvokeException extends ActionException {
        private final InvokeContext ctx;

        public InvokeException(InvokeContext ctx, Throwable cause) {
            super(aaaaaaaaaaaaaaaaaa, cause);
            this.ctx = ctx;
        }

        public InvokeContext getContext() {
            return ctx;
        }
    }

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
}
