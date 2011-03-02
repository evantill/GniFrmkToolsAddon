package com.gni.frmk.tools.addon.data.trigger;

import com.gni.frmk.tools.addon.data.trigger.NativeTrigger.NativeState;
import com.gni.frmk.tools.addon.data.trigger.Trigger.State;
import com.gni.frmk.tools.addon.data.trigger.Trigger.Status;
import com.gni.frmk.tools.addon.data.trigger.Trigger.TemporalStatus;
import com.gni.frmk.tools.addon.data.trigger.Trigger.Type;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 29 sept. 2010
 * Time: 15:36:05
 * To change this template use File | Settings | File Templates.
 */
public class TriggerBuilder {
    String name;
    Type type;
    Status status;
    State jmsExecutionState;
    NativeState nativeRetrievalState;
    NativeState nativeProcessingState;

    private NativeState defineNativeState(IData doc) {
        IDataCursor curDoc = doc.getCursor();
        try {
            String stateValue = IDataUtil.getString(curDoc, "state");
            int dashPos = stateValue.indexOf('-');
            String temporalStateValue = dashPos > 0 ? "TEMPORARY" : "PERMANENT";
            if (dashPos > 0) {
                stateValue = stateValue.substring(0, dashPos);
            }
            stateValue = stateValue.toUpperCase();
            State state = Trigger.State.valueOf(stateValue);
            TemporalStatus temporalState = TemporalStatus.valueOf(temporalStateValue);
            return new NativeState(state, temporalState);
        } finally {
            curDoc.destroy();
        }
    }

    /**
     * use pipeline data from service wm.server.triggers:getTriggerReport.
     *
     * @param doc the datas of one jms trigger
     * @return builder for chain calling
     */
    public TriggerBuilder defineNativeStatus(IData doc) {
        type = Type.NATIVE;
        IDataCursor curDoc = doc.getCursor();
        try {
            name = IDataUtil.getString(curDoc, "name");
            IData properties = IDataUtil.getIData(curDoc, "properties");
            IDataCursor curProperties = properties.getCursor();
            try {
                boolean executeEnabled = IDataUtil.getBoolean(curProperties, "executeEnabled");
                if (executeEnabled) {
                    status = Status.ENABLED;
                } else {
                    status = Status.DISABLED;
                }
            } finally {
                curProperties.destroy();
            }
            nativeRetrievalState = defineNativeState(IDataUtil.getIData(curDoc, "retrievalStatus"));
            nativeProcessingState = defineNativeState(IDataUtil.getIData(curDoc, "processingStatus"));
        } finally {
            curDoc.destroy();
        }
        return this;
    }

    /**
     * use pipeline data from service wm.server.jms:getTriggerReport.
     *
     * @param doc the datas of one jms trigger
     * @return builder for chain calling
     */
    public TriggerBuilder defineJmsStatus(IData doc) {
        type = Trigger.Type.JMS;
        IDataCursor curDoc = doc.getCursor();
        try {
            name = IDataUtil.getString(curDoc, "node_nsName");
            IData trigger = IDataUtil.getIData(curDoc, "trigger");
            IDataCursor curTrigger = trigger.getCursor();
            try {
                int state = IDataUtil.getInt(curTrigger, "state", -1);
                switch (state) {
                    case 0:
                        status = Status.ENABLED;
                        jmsExecutionState = State.ACTIVE;
                        break;
                    case 1:
                        status = Status.DISABLED;
                        jmsExecutionState = State.ACTIVE;
                        break;
                    case 2:
                        status = Status.ENABLED;
                        jmsExecutionState = State.SUSPENDED;
                        break;
                    default:
                        throw new IllegalStateException(String.format("jms trigger %s state %s unknown", name, status));
                }
            } finally {
                curTrigger.destroy();
            }
        } finally {
            curDoc.destroy();
        }
        return this;
    }

    public JmsTrigger buildJmsTrigger() {
        return new JmsTrigger(this);
    }

    public NativeTrigger buildNativeTrigger() {
        return new NativeTrigger(this);
    }

    public TriggerBuilder duplicate(JmsTrigger trigger) {
        name = trigger.getName();
        type = trigger.getType();
        status = trigger.getStatus();
        jmsExecutionState = trigger.getExecutionState();
        return this;
    }

    public TriggerBuilder duplicate(NativeTrigger trigger) {
        name = trigger.getName();
        type = trigger.getType();
        status = trigger.getStatus();
        nativeRetrievalState = trigger.getRetrievalState();
        nativeProcessingState = trigger.getProcessingState();
        return this;
    }

//    public TriggerBuilder open() {
//        status = Status.ENABLED;
//        jmsExecutionState = State.ACTIVE;
//        nativeRetrievalState = new NativeState(State.ACTIVE, TemporalStatus.PERMANENT);
//        nativeProcessingState = new NativeState(State.ACTIVE, TemporalStatus.PERMANENT);
//        return this;
//    }
//
//    public TriggerBuilder close() {
//        status = Status.DISABLED;
//        jmsExecutionState = State.SUSPENDED;
//        nativeRetrievalState = new NativeState(State.SUSPENDED, TemporalStatus.PERMANENT);
//        nativeProcessingState = new NativeState(State.SUSPENDED, TemporalStatus.PERMANENT);
//        return this;
//    }
}

