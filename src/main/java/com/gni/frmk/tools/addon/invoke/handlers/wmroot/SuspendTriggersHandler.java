package com.gni.frmk.tools.addon.invoke.handlers.wmroot;

import com.gni.frmk.tools.addon.invoke.ActionHandler;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.SuspendTriggers;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.invoke.results.NoResult;
import com.wm.data.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:42
 *
 * @author: e03229
 */
public class SuspendTriggersHandler extends AbstractInvokeHandler<SuspendTriggers, NoResult>
        implements ActionHandler<SuspendTriggers, NoResult, InvokeContext> {

    public SuspendTriggersHandler() {
        super("wm.server.triggers:suspendTrigger");
    }

    @Override
    public Class<SuspendTriggers> getActionType() {
        return SuspendTriggers.class;
    }

    @Override
    protected IData prepareInput(SuspendTriggers in) {
        return IDataFactory.create(new Object[][]{
                {"triggerNameList",
                 in.getTriggerNames()},
                {"applyChangeAcrossCluster",
                 in.isApplyChangeAcrossCluster()},
                {"retrievalSuspend",
                 Boolean.toString(in.isRetrievalSuspend())},
                {"processingSuspend",
                 Boolean.toString(in.isProcessingSuspend())},
                {"persistChange",
                 Boolean.toString(in.isPersistChange())}
        });
    }

    @Override
    protected NoResult parseOutput(SuspendTriggers action, IData output) {
        return NoResult.newInstance();
    }
}
