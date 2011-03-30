package com.gni.frmk.tools.addon.invoke;

import com.gni.frmk.tools.addon.dispatcher.Action;
import com.gni.frmk.tools.addon.invoke.util.PipelineTestUtils;
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
    public IData invoke(Action<?> action, NSName service, IData input) throws ServiceInvokeException {
        try {
            return utils.loadServiceOutput(service.getFullName());
        } catch (IOException e) {
            throw new ServiceInvokeException(this, action, service, input, e);
        }
    }

    @Override
    public boolean canInvoke(NSName service) {
        return utils.existServiceOutput(service.getFullName());
    }
}