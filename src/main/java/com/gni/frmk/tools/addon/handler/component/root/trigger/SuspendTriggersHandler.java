package com.gni.frmk.tools.addon.handler.component.root.trigger;

import com.gni.frmk.tools.addon.action.component.root.trigger.SuspendTriggers;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.result.NoResult;
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

    private static final String[] EMPTY_ARRAY=new String[0];

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
                 in.getTriggerNames().toArray(EMPTY_ARRAY)},
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
