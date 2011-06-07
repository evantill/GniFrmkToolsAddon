package com.gni.frmk.tools.addon.invoker.service.jms;

import com.gni.frmk.tools.addon.invoker.api.ServiceInputException;
import com.gni.frmk.tools.addon.invoker.api.ServiceOutputException;
import com.gni.frmk.tools.addon.invoker.io.NoOutput;
import com.gni.frmk.tools.addon.invoker.io.SingleValueInput;
import com.gni.frmk.tools.addon.invoker.service.WmService;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 16:38
 *
 * @author: e03229
 */
public class EnableConnectionAlias extends WmService<SingleValueInput<String>, NoOutput> {

    public EnableConnectionAlias() {
        super("wm.server.jms:enableConnectionAlias");
    }

    @Override
    protected IData prepareInput(SingleValueInput<String> input) throws ServiceInputException {
        return IDataFactory.create(new Object[][]{{"aliasName",
                                                   input.getValue()}});
    }

    @Override
    protected NoOutput prepareOutput(IData output) throws ServiceOutputException {
        return NoOutput.singleton;
    }
}
