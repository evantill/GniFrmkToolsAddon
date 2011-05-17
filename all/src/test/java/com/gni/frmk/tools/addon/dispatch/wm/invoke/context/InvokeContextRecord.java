package com.gni.frmk.tools.addon.dispatch.wm.invoke.context;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.api.Dispatcher;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInvokeException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.util.PipelineTestUtils;
import com.wm.data.*;
import com.wm.lang.ns.NSName;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 16:18
 *
 * @author: e03229
 */
public class InvokeContextRecord implements InvokeContext {
    private final InvokeContext decorated;
    private final PipelineTestUtils utils;

    public InvokeContextRecord(InvokeContext decorated, PipelineTestUtils utils) {
        this.decorated = decorated;
        this.utils = utils;
    }

    @Override
    public IData invoke(Action<?> action, NSName service, IData input) throws ServiceInvokeException {
        try {
            utils.saveServiceInput(service.getFullName(), input);
            IData output = decorated.invoke(action, service, input);
            utils.saveServiceOutput(service.getFullName(), output);
            return output;
        } catch (IOException e) {
            throw new ServiceInputException(this, action, service, new ParseInputException(e,input));
        }
    }

    @Override
    public boolean canInvoke(NSName service) {
        return decorated.canInvoke(service);
    }

    @Override
    public Dispatcher getDispatcher() {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
