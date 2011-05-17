package com.gni.frmk.tools.addon.operation.handler.component.jms.trigger;

import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.model.component.ActivableState;
import com.gni.frmk.tools.addon.operation.handler.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.operation.action.component.jms.trigger.GetJmsTriggerState;
import com.gni.frmk.tools.addon.operation.result.SingleResult;
import com.wm.data.*;

import static com.gni.frmk.tools.addon.model.component.ActivableStatus.ACTIVE;
import static com.gni.frmk.tools.addon.model.component.ActivableStatus.INACTIVE;
import static com.gni.frmk.tools.addon.model.component.EnableStatus.DISABLED;
import static com.gni.frmk.tools.addon.model.component.EnableStatus.ENABLED;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 16:44
 *
 * @author: e03229
 */
public class GetJmsTriggerStateHandler
        extends AbstractInvokeHandler<GetJmsTriggerState, SingleResult<ActivableState>>
        implements ActionHandler<GetJmsTriggerState, SingleResult<ActivableState>, InvokeContext> {

    public GetJmsTriggerStateHandler() {
        super("wm.server.jms:getTriggerReport");
    }

    private ActivableState parseActivableState(String triggerName, IData triggerData) {
        IDataCursor cur = triggerData.getCursor();
        try {
            final int state = IDataUtil.getInt(cur, "state", -1);
            final EnableStatus enabled;
            final ActivableStatus activable;
            switch (state) {
                case 0:
                    enabled = ENABLED;
                    activable = ACTIVE;
                    break;
                case 1:
                    enabled = DISABLED;
                    activable = ACTIVE;
                    break;
                case 2:
                    enabled = ENABLED;
                    activable = INACTIVE;
                    break;
                default:
                    throw new IllegalStateException(String.format("jms trigger %s state %s unknown", triggerName, state));
            }
            return new ActivableState(enabled, activable);
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected SingleResult<ActivableState> parseOutput(GetJmsTriggerState action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            String triggerNameToFind = action.getId().getValue();
            IData[] dataList = IDataUtil.getIDataArray(cur, "triggerDataList");
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String triggerName = IDataUtil.getString(curLoop, "node_nsName");
                        if (!triggerNameToFind.equals(triggerName)) {
                            continue;
                        }
                        ActivableState state = parseActivableState(triggerName, IDataUtil.getIData(curLoop, "trigger"));
                        return new SingleResult<ActivableState>(state);
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new SingleResult<ActivableState>(new ActivableState(EnableStatus.UNKNOWN, ActivableStatus.UNKNOWN));
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(GetJmsTriggerState in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<GetJmsTriggerState> getActionType() {
        return GetJmsTriggerState.class;
    }
}
