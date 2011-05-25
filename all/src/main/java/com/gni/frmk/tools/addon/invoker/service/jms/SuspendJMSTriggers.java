package com.gni.frmk.tools.addon.invoker.service.jms;

import com.gni.frmk.tools.addon.invoker.api.ServiceInputException;
import com.gni.frmk.tools.addon.invoker.api.ServiceOutputException;
import com.gni.frmk.tools.addon.invoker.io.NoOutput;
import com.gni.frmk.tools.addon.invoker.io.jms.JmsTriggerInput;
import com.gni.frmk.tools.addon.invoker.service.WmService;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 17:15
 *
 * @author: e03229
 */
public class SuspendJMSTriggers extends WmService<JmsTriggerInput, NoOutput> {

    public SuspendJMSTriggers() {
        super("wm.server.jms:suspendJMSTriggers");
    }

    @Override
    protected IData prepareInput(JmsTriggerInput input) throws ServiceInputException {
        return IDataFactory.create(new Object[][]{
                {"applyChangeAcrossCluster",
                 input.isApplyChangeAcrossCluster()},
                {"triggerNameList",
                 input.getTriggerNameList()}
        });
    }

    @Override
    protected NoOutput prepareOutput(IData output) throws ServiceOutputException {
        return NoOutput.singleton;
    }
}
