package com.gni.frmk.tools.addon.invoker.service.root;

import com.gni.frmk.tools.addon.invoker.api.ServiceInputException;
import com.gni.frmk.tools.addon.invoker.api.ServiceOutputException;
import com.gni.frmk.tools.addon.invoker.io.NoOutput;
import com.gni.frmk.tools.addon.invoker.io.root.PortInput;
import com.gni.frmk.tools.addon.invoker.service.WmService;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 17:21
 *
 * @author: e03229
 */
public class DisableListener extends WmService<PortInput, NoOutput> {

     DisableListener() {
        super("wm.server.net.listeners:disableListener");
    }

    @Override
    protected IData prepareInput(PortInput input) throws ServiceInputException {
        return IDataFactory.create(new Object[][]{
                {"listenerKey",
                 input.getKey()},
                {"pkg",
                 input.getPackageName()}
        });
    }

    @Override
    protected NoOutput prepareOutput(IData output) throws ServiceOutputException {
        return NoOutput.singleton;
    }
}
