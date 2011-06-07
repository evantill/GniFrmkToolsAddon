package com.gni.frmk.tools.addon.invoker.service.root;

import com.gni.frmk.tools.addon.invoker.api.ServiceInputException;
import com.gni.frmk.tools.addon.invoker.api.ServiceOutputException;
import com.gni.frmk.tools.addon.invoker.io.NoOutput;
import com.gni.frmk.tools.addon.invoker.io.root.NativeTriggerInput;
import com.gni.frmk.tools.addon.invoker.service.WmService;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 19:57
 *
 * @author: e03229
 */
public class SuspendTrigger extends WmService<NativeTriggerInput, NoOutput> {
    public SuspendTrigger() {
        super("wm.server.triggers:suspendTrigger");
    }

    @Override
    protected IData prepareInput(NativeTriggerInput input) throws ServiceInputException {
        return IDataFactory.create(new Object[][]{
                {"triggerNameList",
                 input.getTriggerNameList()},
                {"applyChangeAcrossCluster",
                 input.isApplyChangeAcrossCluster()},
                {"retrievalSuspend",
                 Boolean.toString(input.isRetrievalSuspend())},
                {"processingSuspend",
                 Boolean.toString(input.isProcessingSuspend())},
                {"persistChange",
                 Boolean.toString(input.isPersistChange())}
        });
    }

    @Override
    protected NoOutput prepareOutput(IData output) throws ServiceOutputException {
        return NoOutput.singleton;
    }
}
