package com.gni.frmk.tools.addon.command.handler.wm.jms.trigger;

import com.gni.frmk.tools.addon.command.action.wm.jms.trigger.SuspendJmsTriggers;
import com.gni.frmk.tools.addon.command.api.ActionHandler;
import com.gni.frmk.tools.addon.command.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.command.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.command.result.NoResult;
import com.wm.data.*;

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
