package com.gni.frmk.tools.addon.handler.component.jms.trigger;

import com.gni.frmk.tools.addon.action.component.jms.trigger.EnableJmsTriggers;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.result.NoResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.api.action.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 17:01
 *
 * @author: e03229
 */
public class EnableJmsTriggersHandler extends AbstractInvokeHandler<EnableJmsTriggers, NoResult>
        implements ActionHandler<EnableJmsTriggers, NoResult, InvokeContext> {

    public EnableJmsTriggersHandler() {
        super("wm.server.jms:enableJMSTriggers");
    }

    @Override
    protected IData prepareInput(EnableJmsTriggers in) {
        return IDataFactory.create(new Object[][]{
                {"triggerNameList",
                 in.getTriggerNames().toArray()},
                {"applyChangeAcrossCluster",
                 in.isApplyChangeAcrossCluster()}
        });
    }

    @Override
    protected NoResult parseOutput(EnableJmsTriggers action, IData output) {
        return NoResult.newInstance();
    }

    @Override
    public Class<EnableJmsTriggers> getActionType() {
        return EnableJmsTriggers.class;
    }
}
