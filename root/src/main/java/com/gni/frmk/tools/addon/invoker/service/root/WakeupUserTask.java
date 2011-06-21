package com.gni.frmk.tools.addon.invoker.service.root;

import com.gni.frmk.tools.addon.invoker.api.ServiceInputException;
import com.gni.frmk.tools.addon.invoker.api.ServiceOutputException;
import com.gni.frmk.tools.addon.invoker.io.NoOutput;
import com.gni.frmk.tools.addon.invoker.io.SingleValueInput;
import com.gni.frmk.tools.addon.invoker.service.WmService;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 18:41
 *
 * @author: e03229
 */
public class WakeupUserTask extends WmService<SingleValueInput<String>, NoOutput> {

     WakeupUserTask() {
        super("wm.server.schedule:wakeupUserTask");
    }

    @Override
    protected IData prepareInput(SingleValueInput<String> input) throws ServiceInputException {
        return IDataFactory.create(new Object[][]{
                {"oid",
                 input.getValue()}
        });
    }

    @Override
    protected NoOutput prepareOutput(IData output) throws ServiceOutputException {
        return NoOutput.singleton;
    }
}
