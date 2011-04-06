package com.gni.frmk.tools.addon.command.handler.wm;

import com.gni.frmk.tools.addon.command.api.Action;
import com.gni.frmk.tools.addon.command.api.Result;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.ServiceInvokeException;
import com.wm.data.*;
import com.wm.lang.ns.NSName;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 15:36
 *
 * @author: e03229
 */
public abstract class AbstractInvokeHandler<A extends Action<R>, R extends Result> implements InvokeHandler<A, R> {
    protected static final IData EMPTY_INPUT = IDataFactory.create();

    protected final NSName service;

    public AbstractInvokeHandler(String serviceName) {service = NSName.create(serviceName);}

    public R execute(A action, InvokeContext context) throws ServiceInvokeException {
        IData input = prepareInput(action);
        IData output = context.invoke(action, service, input);
        return parseOutput(action, output);
    }

    protected abstract R parseOutput(A action, IData output);

    protected abstract IData prepareInput(A in);

    public NSName getService() {
        return NSName.create(service.getFullName());
    }
}