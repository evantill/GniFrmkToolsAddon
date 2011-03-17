package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.invoke.exceptions.InvokeException;
import com.gni.frmk.tools.addon.invoke.exceptions.ServiceInvokeException;
import com.gni.frmk.tools.addon.invoke.utils.PipelineTestUtils;
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
public class InvokeContextRecord extends InvokeContext {
    private final InvokeContext decorated;
    private final PipelineTestUtils utils;

    public InvokeContextRecord(InvokeContext decorated, PipelineTestUtils utils) {
        this.decorated = decorated;
        this.utils = utils;
    }

    @Override
    public IData invoke(NSName service, IData input) throws ServiceInvokeException {
        try {
            utils.saveServiceInput(service.getFullName(), input);
            try {
                IData output = decorated.invoke(service, input);
                utils.saveServiceOutput(service.getFullName(), output);
                return output;
            } catch (InvokeException caught) {
                throw new ServiceInvokeException(this, service, input, caught);
            }
        } catch (IOException e) {
            throw new ServiceInvokeException(this, service, input, e);
        }
    }

    @Override
    public boolean canInvoke(NSName service) {
        return decorated.canInvoke(service);
    }
}
