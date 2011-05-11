package com.gni.frmk.tools.addon.handler.wm;

import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInvokeException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.wm.data.*;
import com.wm.lang.ns.NSName;
import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.Result;

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
        try {
            IData input = prepareInput(action);
            IData output = context.invoke(action, service, input);
            return parseOutput(action, output);
        } catch (ParseOutputException e) {
            throw new ServiceOutputException(context, action, service, e);
        } catch (ParseInputException e) {
            throw new ServiceInputException(context, action, service, e);
        }
    }

    protected abstract R parseOutput(A action, IData output) throws ParseOutputException;

    protected abstract IData prepareInput(A action) throws ParseInputException;

    public NSName getService() {
        return NSName.create(service.getFullName());
    }
}
