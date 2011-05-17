package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.BaseComponent.AbstractState;
import com.gni.frmk.tools.addon.model.component.EnableState;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/03/11
 * Time: 16:32
 *
 * @author: e03229
 */
public final class NativeTriggerState extends AbstractState<NativeTriggerState> {

    private EnableState enabled;
    private TemporaryActivableState retrievalState;
    private TemporaryActivableState processingState;

    public NativeTriggerState() {
        super(false);
    }

    public EnableState getEnabled() {
        return enabled;
    }

    public void setEnabled(EnableState enabled) {
        this.enabled = enabled;
    }

    public TemporaryActivableState getRetrievalState() {
        return retrievalState;
    }

    public void setRetrievalState(TemporaryActivableState retrievalState) {
        this.retrievalState = retrievalState;
    }

    public TemporaryActivableState getProcessingState() {
        return processingState;
    }

    public void setProcessingState(TemporaryActivableState processingState) {
        this.processingState = processingState;
    }

    @Override
    public int compareTo(NativeTriggerState other) {
        return ComparisonChain.start()
                              .compare(0, super.compareTo(other))
                              .compare(getEnabled(), other.getEnabled())
                              .compare(getProcessingState(), other.getProcessingState())
                              .compare(getRetrievalState(), other.getRetrievalState())
                              .result();
    }

    @Override
    public boolean isUnknown() {
        return getEnabled().isUnknown() || getProcessingState().isUnknown() || getRetrievalState().isUnknown();
    }


    public static NativeTriggerState newNativeTriggerState(EnableState enabled, TemporaryActivableState retrievalState, TemporaryActivableState processingState) {
        NativeTriggerState result = new NativeTriggerState();
        result.setEnabled(enabled);
        result.setRetrievalState(retrievalState);
        result.setProcessingState(processingState);
        return result;
    }

}
