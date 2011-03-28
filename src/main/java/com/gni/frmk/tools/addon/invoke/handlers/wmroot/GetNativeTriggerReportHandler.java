package com.gni.frmk.tools.addon.invoke.handlers.wmroot;

import com.gni.frmk.tools.addon.configuration.components.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.configuration.components.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.configuration.components.NativeTrigger;
import com.gni.frmk.tools.addon.configuration.components.NativeTriggerState;
import com.gni.frmk.tools.addon.configuration.components.TemporaryActivableState;
import com.gni.frmk.tools.addon.configuration.components.TemporaryActivableState.TemporaryStatus;
import com.gni.frmk.tools.addon.dispatcher.ActionHandler;
import com.gni.frmk.tools.addon.dispatcher.ListResult;
import com.gni.frmk.tools.addon.invoke.actions.wmroot.GetNativeTriggerReport;
import com.gni.frmk.tools.addon.invoke.InvokeContext;
import com.gni.frmk.tools.addon.invoke.handlers.AbstractInvokeHandler;
import com.google.common.collect.Lists;
import com.wm.data.*;

import java.util.List;

import static com.gni.frmk.tools.addon.configuration.components.TemporaryActivableState.TemporaryStatus.PERMANENT;
import static com.gni.frmk.tools.addon.configuration.components.TemporaryActivableState.TemporaryStatus.TEMPORARY;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 10:37
 *
 * @author: e03229
 */
public class GetNativeTriggerReportHandler extends AbstractInvokeHandler<GetNativeTriggerReport, ListResult<NativeTrigger>>
        implements ActionHandler<GetNativeTriggerReport, ListResult<NativeTrigger>, InvokeContext> {

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
    protected ListResult<NativeTrigger> parseOutput(GetNativeTriggerReport action, IData output) {
        IDataCursor cur = output.getCursor();
        try {
            List<NativeTrigger> values = Lists.newArrayList();
            IData[] dataList = IDataUtil.getIDataArray(cur, "triggers");
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        values.add(NativeTrigger.builder()
                                                .name(IDataUtil.getString(curLoop, "name"))
                                                .packageName(IDataUtil.getString(curLoop, "packageName"))
                                                .defineState(parseTriggerState(curLoop))
                                                .build());
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ListResult<NativeTrigger>(values);
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
