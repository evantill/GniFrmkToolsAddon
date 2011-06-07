package com.gni.frmk.tools.addon.operation.handler.component.jms.trigger.oldies;

import com.gni.frmk.tools.addon.operation.action.component.jms.trigger.SuspendJmsTriggers;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.result.NoResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 17:01
 *
 * @author: e03229
 */
public class SuspendJmsTriggersHandler extends AbstractInvokeHandler<SuspendJmsTriggers, NoResult>
        implements ActionHandler<SuspendJmsTriggers, NoResult, InvokeContext> {

    public SuspendJmsTriggersHandler() {
        super("wm.server.jms:suspendJMSTriggers");
    }

    @Override
    protected IData prepareInput(SuspendJmsTriggers in) {
        return IDataFactory.create(new Object[][]{
                {"triggerNameList",
                 in.getTriggerNames().toArray()},
                {"applyChangeAcrossCluster",
                 in.isApplyChangeAcrossCluster()}
        });
    }

    @Override
    protected NoResult parseOutput(SuspendJmsTriggers action, IData output) {
        return NoResult.newInstance();
    }

    @Override
    public Class<SuspendJmsTriggers> getActionType() {
        return SuspendJmsTriggers.class;
    }
}
