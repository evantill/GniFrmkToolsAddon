package com.gni.frmk.tools.addon.handler.wm.root.trigger;

import com.gni.frmk.tools.addon.action.wm.root.trigger.GetNativeTriggerState;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceInputException.ParseInputException;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.ServiceOutputException.ParseOutputException;
import com.gni.frmk.tools.addon.handler.wm.AbstractInvokeHandler;
import com.gni.frmk.tools.addon.model.component.state.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.model.component.state.EnableState.EnableStatus;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState.TemporaryActivableState;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState.TemporaryStatus;
import com.gni.frmk.tools.addon.result.ComponentStateResult;
import com.wm.data.*;
import com.gni.frmk.tools.addon.api.action.ActionHandler;

/**
 * Created by IntelliJ IDEA.
 * Date: 10/05/11
 * Time: 14:31
 *
 * @author: e03229
 */
public class GetNativeTriggerStateHandler
        extends AbstractInvokeHandler<GetNativeTriggerState, ComponentStateResult<NativeTriggerState>>
        implements ActionHandler<GetNativeTriggerState, ComponentStateResult<NativeTriggerState>, InvokeContext> {

    public GetNativeTriggerStateHandler() {
        super("wm.server.triggers:getTriggerReport");
    }

    @Override
    public Class<GetNativeTriggerState> getActionType() {
        return GetNativeTriggerState.class;
    }

    @Override
    protected ComponentStateResult<NativeTriggerState> parseOutput(GetNativeTriggerState action, IData output) throws ParseOutputException {
        IDataCursor cur = output.getCursor();
        try {
            IData[] dataList = IDataUtil.getIDataArray(cur, "triggers");
            String nameToFind = action.getId().getValue();
            if (dataList != null) {
                for (IData single : dataList) {
                    IDataCursor curLoop = single.getCursor();
                    try {
                        String name = IDataUtil.getString(curLoop, "name");
                        if (!nameToFind.equals(name)) {
                            continue;
                        }
                        NativeTriggerState nativeTriggerState = parseTriggerState(curLoop);
                        return new ComponentStateResult<NativeTriggerState>(nativeTriggerState);
                    } finally {
                        curLoop.destroy();
                    }
                }
            }
            return new ComponentStateResult<NativeTriggerState>(emptyTriggerState());
        } finally {
            cur.destroy();
        }
    }

    private NativeTriggerState emptyTriggerState() {
        NativeTriggerState result = new NativeTriggerState();
        result.setEnabled(new EnableState(EnableStatus.UNKNOWN));
        result.setProcessingState(new TemporaryActivableState(NativeTriggerState.TemporaryStatus.UNKNOWN, ActivableStatus.UNKNOWN));
        result.setRetrievalState(new TemporaryActivableState(NativeTriggerState.TemporaryStatus.UNKNOWN, ActivableStatus.UNKNOWN));
        return result;
    }

    @Override
    protected IData prepareInput(GetNativeTriggerState action) throws ParseInputException {
        return EMPTY_INPUT;
    }

    private NativeTriggerState parseTriggerState(IDataCursor cur) {
        NativeTriggerState result = new NativeTriggerState();

        EnableStatus enabled = parseEnabled(IDataUtil.getIData(cur, "properties"));
        result.setEnabled(new EnableState(enabled));

        TemporaryActivableState processingState = parseTmpActState(IDataUtil.getIData(cur, "processingStatus"));
        result.setProcessingState(processingState);

        TemporaryActivableState retrievalState = parseTmpActState(IDataUtil.getIData(cur, "retrievalStatus"));
        result.setRetrievalState(retrievalState);

        return result;
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
            TemporaryStatus temporary = TemporaryStatus.PERMANENT;
            {
                int dashPos = stateValue.indexOf('-');
                if (dashPos > 0) {
                    temporary = TemporaryStatus.TEMPORARY;
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
}
