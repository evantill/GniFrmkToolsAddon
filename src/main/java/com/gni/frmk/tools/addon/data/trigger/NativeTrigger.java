package com.gni.frmk.tools.addon.data.trigger;

import com.gni.frmk.tools.addon.data.ConfigurationElement;
import com.gni.frmk.tools.addon.operation.visitor.ConfigurationVisitor;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 29 sept. 2010
 * Time: 15:17:45
 * To change this template use File | Settings | File Templates.
 */
public class NativeTrigger extends Trigger implements Comparable<NativeTrigger>, ConfigurationElement<NativeTrigger> {

    public static final class NativeState implements Comparable<NativeState> {
        private State state;
        private TemporalStatus temporalState;

        public NativeState(State state, TemporalStatus temporalState) {
            this.state = state;
            this.temporalState = temporalState;
        }

        /**
         * Empty constructor used by jaxb.
         */
        private NativeState(){}

        public State getState() {
            return state;
        }

        public TemporalStatus getTemporalState() {
            return temporalState;
        }

        public boolean isSuspended() {
            return State.SUSPENDED.equals(state);
        }

        public boolean isPermanent() {
            return TemporalStatus.PERMANENT.equals(temporalState);
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this).add("state", getState()).add("temporalState", getTemporalState()).toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            NativeState that = (NativeState) o;

            return Objects.equal(getState(), that.getState()) && Objects.equal(getTemporalState(), that.getTemporalState());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(getState(), getTemporalState());
        }

        public int compareTo(NativeState that) {
            return ComparisonChain.start().compare(getState(), that.getState()).compare(getTemporalState(), that.getTemporalState()).result();
        }

    }

    private NativeState retrievalState;
    private NativeState processingState;

    public NativeTrigger(String name, Status status, NativeState retrievalState, NativeState processingState) {
        super(name, status, Type.NATIVE);
        this.retrievalState = retrievalState;
        this.processingState = processingState;
    }

    public NativeTrigger(TriggerBuilder builder) {
        super(builder);
        retrievalState = builder.nativeRetrievalState;
        processingState = builder.nativeProcessingState;
    }

    /**
     * Empty constructor only for jaxb.
     */
    private NativeTrigger() {
    }

    public void accept(ConfigurationVisitor visitor) {
        //visitor.visit(this);
    }

    public NativeState getRetrievalState() {
        return retrievalState;
    }

    public void setRetrievalState(NativeState retrievalState) {
        this.retrievalState = retrievalState;
    }

    public NativeState getProcessingState() {
        return processingState;
    }

    public void setProcessingState(NativeState processingState) {
        this.processingState = processingState;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("name", getName()).add("type", getType()).add("status", getStatus()).add("retrievalState", getRetrievalState()).add("processingState", getProcessingState()).toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int compareTo(NativeTrigger that) {
        return ComparisonChain.start().compare(getType(), that.getType()).compare(getName(), that.getName()).result();
    }

    /**
     * Note: this class has a natural ordering that is inconsistent with equals.
     *
     * @param that element to compare
     * @return 0 if same status
     */
    public int compareStatusTo(NativeTrigger that) {
        return ComparisonChain.start().compare(compareTo(that), 0).compare(getStatus(), that.getStatus()).compare(getProcessingState(), that.getProcessingState()).compare(getRetrievalState(), that.getRetrievalState()).result();
    }

}
