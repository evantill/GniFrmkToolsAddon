package com.gni.frmk.tools.addon.invoke;

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
    public IData invoke(NSName service, IData input) throws InvokeException {
        try {
            utils.saveServiceInput(service.getFullName(), input);
            IData output = super.invoke(service, input);
            utils.saveServiceOutput(service.getFullName(), output);
            return output;
        } catch (IOException e) {
            throw new ServiceInvokeException(this, service, input, e);
        }
    }
}
