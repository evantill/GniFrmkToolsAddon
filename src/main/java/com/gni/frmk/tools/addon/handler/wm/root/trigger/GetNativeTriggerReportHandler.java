package com.gni.frmk.tools.addon.handler.wm.root.trigger;

import com.gni.frmk.tools.addon.action.wm.root.trigger.GetNativeTriggerReport;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.ImmutableNativeTrigger.MutableNativeTrigger;
import com.gni.frmk.tools.addon.model.component.state.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.state.TemporaryActivableState;
import com.gni.frmk.tools.addon.model.component.state.TemporaryActivableState.TemporaryStatus;
import com.gni.frmk.tools.addon.result.ListResult;
import com.google.common.collect.Maps;
import com.wm.data.*;

import java.util.Map;

import static com.gni.frmk.tools.addon.model.component.state.TemporaryActivableState.TemporaryStatus.PERMANENT;
import static com.gni.frmk.tools.addon.model.component.state.TemporaryActivableState.TemporaryStatus.TEMPORARY;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 10:37
 *
 * @author: e03229
 */
public class GetNativeTriggerReportHandler extends AbstractInvokeHandler<GetNativeTriggerReport, ListResult<MutableNativeTrigger>>
        implements ActionHandler<GetNativeTriggerReport, ListResult<MutableNativeTrigger>, InvokeContext> {

    public GetNativeTriggerReportHandler() {
        super("wm.server.triggers:getTriggerReport");
    }

    private NativeTriggerState parseTriggerState(IDataCursor cur) {
        EnableStatus enabled = parseEnabled(IDataUtil.getIData(cur, "properties"));
        TemporaryActivableState retrievalState = parseTmpActState(IDataUtil.getIData(cur, "retrievalStatus"));
        TemporaryActivableState processingState = parseTmpActState(IDataUtil.getIData(cur, "processingStatus"));
        return NativeTriggerState.builder()
                                 .defineEnable(enabled)
                                 .defineRetrieval(retrievalState)
                                 .defineProcessing(processingState)
                                 .build();
    }

    private EnableStatus parseEnabled(IData properties) {
        IDataCursor cur = properties.getCursor();
        try {
            return EnableStatus.fromBooleanString(IDataUtil.getString(cur, "executeEnabled"));
        } finally {
            cur.destroy();
        }
    }

    private TemporaryActivableState parseTmpActState(IData doc) {
        //case of a trigger without subscription
        if (doc == null) {
            //TODO add log
            return new TemporaryActivableState(TemporaryStatus.PERMANENT, ActivableStatus.INACTIVE);
        }
        IDataCursor curDoc = doc.getCursor();
        try {
            String stateValue = IDataUtil.getString(curDoc, "state");
            TemporaryStatus temporary = PERMANENT;
            {
                int dashPos = stateValue.indexOf('-');
                if (dashPos > 0) {
                    temporary = TEMPORARY;
                    stateValue = stateValue.substring(0, dashPos);
                }
            }
            //ACTIVE or SUSPENDED
            ActivableStatus activable = ActivableStatus.fromStateString(stateValue.toUpperCase(), "ACTIVE", "SUSPENDED");
            return new TemporaryActivableState(temporary, activable);
        } finally {
            curDoc.destroy();
        }
    }

    @Override
    protected ListResult<MutableNativeTrigger> parseOutput(GetNativeTriggerReport action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            Map<String, MutableNativeTrigger> values = Maps.newHashMap();
            for (MutableNativeTrigger value : action.getCollection()) {
                values.put(value.getComponentId().asString(), value);
            }
            IData[] dataList = IDataUtil.getIDataArray(cur, "triggers");
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String name = IDataUtil.getString(curLoop, "name");
                        MutableNativeTrigger value = values.get(name);
                        if (value == null && action.isUpdate()) {
                            continue;
                        } else if (value == null && !action.isUpdate()) {
                            value = new MutableNativeTrigger();
                            value.setName(name);
                        }
                        value.setState(parseTriggerState(curLoop));
                        values.put(value.getComponentId().asString(), value);
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<MutableNativeTrigger>(values.values());
        } finally {
            cur.destroy();
        }
    }

    @Override
    protected IData prepareInput(GetNativeTriggerReport in) {
        return EMPTY_INPUT;
    }

    @Override
    public Class<GetNativeTriggerReport> getActionType() {
        return GetNativeTriggerReport.class;
    }
}
