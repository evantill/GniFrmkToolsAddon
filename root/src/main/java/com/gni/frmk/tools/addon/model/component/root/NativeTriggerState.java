package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.BaseComponent.AbstractState;
import com.gni.frmk.tools.addon.model.component.ActivableState.ActivableStatus;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/03/11
 * Time: 16:32
 *
 * @author: e03229
 */
public class NativeTriggerState extends AbstractState {

    public static enum TemporaryStatus {
        UNKNOWN(false), TEMPORARY(false), PERMANENT(true);

        private final boolean permanent;

        TemporaryStatus(boolean permanent) {
            this.permanent = permanent;
        }

        public final boolean isPermanent() {
            return permanent;
        }

        public boolean isTemporary() {
            return !permanent;
        }
    }

    public static class TemporaryActivableState extends AbstractState {
        private TemporaryStatus temporary;
        private ActivableStatus activable;

        public TemporaryActivableState(TemporaryStatus temporary, ActivableStatus activable) {
            super(activable != ActivableStatus.UNKNOWN);
            this.temporary = temporary;
            this.activable = activable;
        }

        public TemporaryActivableState() {
            super(false);
        }

        public TemporaryStatus getTemporary() {
            return temporary;
        }

        public void setTemporary(TemporaryStatus temporary) {
            this.temporary = temporary;
        }

        public ActivableStatus getActivable() {
            return activable;
        }

        public void setActivable(ActivableStatus activable) {
            this.activable = activable;
        }
    }

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

    public static NativeTriggerState newNativeTriggerState(EnableState enabled, TemporaryActivableState retrievalState, TemporaryActivableState processingState) {
//        NativeTriggerState result = new NativeTriggerState();
//        result.setEnabled(enabled);
//        result.setRetrievalState(retrievalState);
//        result.setProcessingState(processingState);
//        return result;
        return null;
    }

    public static NativeTriggerState newNativeTriggerState() {
//        TemporaryActivableState unknownState = new TemporaryActivableState(TemporaryStatus.UNKNOWN, ActivableStatus.UNKNOWN);
//        return newNativeTriggerState(new EnableState(EnableStatus.UNKNOWN), unknownState, unknownState);
        return null;
    }

}
