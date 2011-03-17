package com.gni.frmk.tools.addon.invoke.handlers;

import com.gni.frmk.tools.addon.invoke.Action;
import com.gni.frmk.tools.addon.invoke.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.Result;
import com.gni.frmk.tools.addon.invoke.exceptions.InvokeException;
import com.wm.data.*;
import com.wm.lang.ns.NSName;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 15:36
 *
 * @author: e03229
 */
public abstract class AbstractInvokeHandler<A extends Action<R>, R extends Result> implements ActionHandler<A, R, InvokeContext> {
    protected static final IData EMPTY_INPUT = IDataFactory.create();

    protected final NSName service;

    public AbstractInvokeHandler(String serviceName) {service = NSName.create(serviceName);}

    public R execute(A action, InvokeContext context) throws InvokeException {
        IData input = prepareInput(action);
        IData output = context.invoke(service, input);
        return parseOutput(action,output);
    }

    protected abstract R parseOutput(A action,IData output);

    protected abstract IData prepareInput(A in);

    public NSName getService() {
        return NSName.create(service.getFullName());
    }
}
