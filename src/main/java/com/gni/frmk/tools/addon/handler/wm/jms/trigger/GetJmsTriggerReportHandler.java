package com.gni.frmk.tools.addon.handler.wm.jms.trigger;

import com.gni.frmk.tools.addon.action.wm.jms.trigger.GetJmsTriggerReport;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.ImmutableJmsTrigger;
import com.gni.frmk.tools.addon.model.component.ImmutableJmsTrigger.MutableJmsTrigger;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.model.component.state.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.result.ListResult;
import com.google.common.collect.Maps;
import com.wm.data.*;

import java.util.Map;

import static com.gni.frmk.tools.addon.model.component.state.ActivableState.ActivableStatus.ACTIVE;
import static com.gni.frmk.tools.addon.model.component.state.ActivableState.ActivableStatus.INACTIVE;
import static com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus.DISABLED;
import static com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus.ENABLED;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 10:37
 *
 * @author: e03229
 */
public class GetJmsTriggerReportHandler extends AbstractInvokeHandler<GetJmsTriggerReport, ListResult<MutableJmsTrigger>>
        implements ActionHandler<GetJmsTriggerReport, ListResult<MutableJmsTrigger>, InvokeContext> {

    public GetJmsTriggerReportHandler() {
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
    protected ListResult<MutableJmsTrigger> parseOutput(GetJmsTriggerReport action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            Map<String, MutableJmsTrigger> values = Maps.newHashMap();
            for (MutableJmsTrigger trigger : action.getCollection()) {
                values.put(trigger.getComponentId().asString(), trigger);
            }
            IData[] dataList = IDataUtil.getIDataArray(cur, "triggerDataList");
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String triggerName = IDataUtil.getString(curLoop, "node_nsName");
                        MutableJmsTrigger value = values.get(triggerName);
                        if (value == null && action.isUpdate()) {
                            continue;
                        } else if (value == null && !action.isUpdate()) {
                            value = new MutableJmsTrigger();
                            value.setName(triggerName);
                            value.setPackageName(IDataUtil.getString(curLoop, "node_pkg"));
                        }
                        ActivableState state = parseActivableState(triggerName, IDataUtil.getIData(curLoop, "trigger"));
                        //add
                        value.setState(state);
                        values.put(value.getComponentId().asString(), value);
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<MutableJmsTrigger>(values.values());
        } finally {
            cur.destroy();
        }

    }

    @Override
    protected IData prepareInput(GetJmsTriggerReport in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<GetJmsTriggerReport> getActionType() {
        return GetJmsTriggerReport.class;
    }
}
