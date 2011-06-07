package com.gni.frmk.tools.addon.operation.handler.component.jms.trigger.oldies;

import com.gni.frmk.tools.addon.operation.action.component.jms.trigger.DisableJmsTriggers;
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
public class DisableJmsTriggersHandler
        extends AbstractInvokeHandler<DisableJmsTriggers, NoResult>
        implements ActionHandler<DisableJmsTriggers, NoResult, InvokeContext> {

    public DisableJmsTriggersHandler() {
        super("wm.server.jms:disableJMSTriggers");
    }

    @Override
    protected IData prepareInput(DisableJmsTriggers in) {
        return IDataFactory.create(new Object[][]{
                {"triggerNameList",
                 in.getTriggerNames().toArray()},
                {"applyChangeAcrossCluster",
                 in.isApplyChangeAcrossCluster()}
        });
    }

    @Override
    protected NoResult parseOutput(DisableJmsTriggers action, IData output) {
        return NoResult.newInstance();
    }

    @Override
    public Class<DisableJmsTriggers> getActionType() {
        return DisableJmsTriggers.class;
    }
}
