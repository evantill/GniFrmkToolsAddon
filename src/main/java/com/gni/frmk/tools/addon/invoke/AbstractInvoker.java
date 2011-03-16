package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.invoke.ActionPattern.ActionException;
import com.wm.data.*;
import com.wm.lang.ns.NSName;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 15:36
 *
 * @author: e03229
 */
public abstract class AbstractInvoker<A, R> {
    protected static final IData EMPTY_INPUT = IDataFactory.create();

    protected final NSName service;

    public AbstractInvoker(String serviceName) {service = NSName.create(serviceName);}

    public R execute(A action, InvokeContext context) throws ActionException {
        IData input=prepareInput(action);
        IData output = context.invoke(service, input);
        return parseOutput(output);
    }

    protected abstract R parseOutput(IData output);

    protected abstract IData prepareInput(A in);

}
