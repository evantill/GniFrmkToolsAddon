package com.gni.frmk.tools.addon.invoker.service;

import com.gni.frmk.tools.addon.invoker.api.ServiceInputException;
import com.gni.frmk.tools.addon.invoker.api.ServiceOutputException;
import com.gni.frmk.tools.addon.invoker.io.NoInput;
import com.gni.frmk.tools.addon.invoker.io.SingleValueOutput;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 10:02
 *
 * @author: e03229
 */
public class PingService extends WmService<NoInput, SingleValueOutput<String>> {

    public PingService() {
        super("wm.server:ping");
    }

    @Override
    protected IData prepareInput(NoInput input) throws ServiceInputException {
        return EMPTY_IDATA;
    }

    @Override
    protected SingleValueOutput<String> prepareOutput(IData output) throws ServiceOutputException {
        String date = extractStringValue(checkDataExist(output), "date", true,null);
        return SingleValueOutput.newInstance(date);
    }

}
