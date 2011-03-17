package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.invoke.exceptions.ServiceInvokeException;
import com.gni.frmk.tools.addon.invoke.utils.PipelineTestUtils;
import com.wm.data.*;
import com.wm.lang.ns.NSName;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/03/11
 * Time: 16:19
 *
 * @author: e03229
 */
public class InvokeContextReplay extends InvokeContext {
    private final PipelineTestUtils utils;

    public InvokeContextReplay(PipelineTestUtils utils) {
        this.utils = utils;
    }

    @Override
    public IData invoke(NSName service, IData input) throws ServiceInvokeException {
        try {
            return utils.loadServiceOutput(service.getFullName());
        } catch (IOException e) {
            throw new ServiceInvokeException(this, service, input, e);
        }
    }

    @Override
    public boolean canInvoke(NSName service) {
        return utils.existServiceOutput(service.getFullName());
    }
}