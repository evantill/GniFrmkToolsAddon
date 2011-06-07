package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.EnableState;
import com.gni.frmk.tools.addon.model.component.base.BaseComponentState;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/03/11
 * Time: 16:32
 *
 * @author: e03229
 */
@XmlRootElement
@XmlType(propOrder = {
        "enabled",
        "retrievalState",
        "processingState"
})
public final class NativeTriggerState extends BaseComponentState<NativeTriggerState> {

    private EnableState enabled;
    private TemporaryActivableState retrievalState;
    private TemporaryActivableState processingState;

    private NativeTriggerState() {
    }

    public NativeTriggerState(Builder builder) {
        super(builder);
        enabled = builder.enabled;
        retrievalState = builder.retrievalState;
        processingState = builder.processingState;
    }

    @XmlElement
    public EnableState getEnabled() {
        return enabled;
    }

    private void setEnabled(EnableState enabled) {
        this.enabled = enabled;
    }

    @XmlElement
    public TemporaryActivableState getRetrievalState() {
        return retrievalState;
    }

    private void setRetrievalState(TemporaryActivableState retrievalState) {
        this.retrievalState = retrievalState;
    }

    @XmlElement
    public TemporaryActivableState getProcessingState() {
        return processingState;
    }

    private void setProcessingState(TemporaryActivableState processingState) {
        this.processingState = processingState;
    }

    @Override
    protected ComparisonChain extendedCompareTo(ComparisonChain chain, NativeTriggerState other) {
        return chain.compare(getEnabled(), other.getEnabled())
                    .compare(getProcessingState(), other.getProcessingState())
                    .compare(getRetrievalState(), other.getRetrievalState());
    }

    @Override
    protected boolean extendedEquals(NativeTriggerState other) {
        return Objects.equal(retrievalState, other.retrievalState)
               && Objects.equal(processingState, other.processingState);
    }

    @Override
    protected Object[] extendedHashCode() {
        return new Object[]{enabled,
                            retrievalState,
                            processingState};
    }

    @Override
    public boolean unknown() {
        return getEnabled().unknown() || getProcessingState().unknown() || getRetrievalState().unknown();
    }

    public static Builder builder() {
        return new Builder();
    }

    @XmlTransient
    public static final class Builder
            extends BaseComponentState.Builder<Builder, NativeTriggerState> {
        private EnableState enabled;
        private TemporaryActivableState retrievalState;
        private TemporaryActivableState processingState;

        public Builder enabled(EnableState value) {
            enabled = checkNotNull(value);
            updateExist();
            return this;
        }

        public Builder retrievalState(TemporaryActivableState value) {
            retrievalState = checkNotNull(value);
            updateExist();
            return this;
        }

        public Builder processingState(TemporaryActivableState value) {
            processingState = checkNotNull(value);
            updateExist();
            return this;
        }

        private void updateExist() {
            boolean wellDefined = enabled != null && retrievalState != null && processingState != null;
            if (wellDefined) {
                boolean unknown = enabled.unknown() || retrievalState.unknown() || processingState.unknown();
                exist(!unknown);
            } else {
                exist(false);
            }
        }

        @Override
        public Builder validate() {
            checkNotNull(enabled);
            checkNotNull(retrievalState);
            checkNotNull(processingState);
            return super.validate();
        }

        @Override
        public NativeTriggerState build() {
            return new NativeTriggerState(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
