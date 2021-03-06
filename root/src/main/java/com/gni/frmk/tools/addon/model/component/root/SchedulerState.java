package com.gni.frmk.tools.addon.model.component.root;

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
        "suspended",
        "scheduled"
})
public class SchedulerState
        extends BaseComponentState<SchedulerState> {

    public static final SchedulerState OPENED = build(SuspendedStatus.READY, SchedulerStatus.UNEXPIRED);
    public static final SchedulerState CLOSED = build(SuspendedStatus.SUSPENDED, SchedulerStatus.EXPIRED);
    public static final SchedulerState UNKNOWN = build(SuspendedStatus.UNKNOWN, SchedulerStatus.UNKNOWN);

    private SuspendedStatus suspended;
    private SchedulerStatus scheduled;

    private SchedulerState() {
    }

    public SchedulerState(Builder builder) {
        super(builder);
        this.suspended = builder.suspended;
        this.scheduled = builder.scheduled;
    }

    @XmlElement
    public SchedulerStatus getScheduled() {
        return scheduled;
    }

    private void setScheduled(SchedulerStatus scheduled) {
        this.scheduled = scheduled;
    }

    @XmlElement
    public SuspendedStatus getSuspended() {
        return suspended;
    }

    private void setSuspended(SuspendedStatus suspended) {
        this.suspended = suspended;
    }

    @Override
    public boolean unknown() {
        return suspended == SuspendedStatus.UNKNOWN || scheduled == SchedulerStatus.UNKNOWN;
    }

    @Override
    public SchedulerState getOpenState() {
        return OPENED;
    }

    @Override
    public SchedulerState getCloseState() {
        return CLOSED;
    }

    @Override
    protected ComparisonChain extendedCompareTo(ComparisonChain chain, SchedulerState other) {
        return chain.compare(getSuspended(), other.getSuspended())
                    .compare(getScheduled(), other.getScheduled());
    }

    @Override
    protected boolean extendedEquals(SchedulerState other) {
        return Objects.equal(suspended, other.suspended)
               && Objects.equal(scheduled, other.scheduled);
    }

    @Override
    protected Object[] extendedHashCode() {
        return new Object[]{scheduled,
                            suspended};
    }

    public static Builder builder() {
        return new Builder();
    }

    public static SchedulerState build(SuspendedStatus suspended, SchedulerStatus scheduler) {
        return builder().scheduled(scheduler).suspended(suspended).validate().build();
    }

    @XmlTransient
    public static final class Builder
            extends BaseComponentState.Builder<Builder, SchedulerState> {
        private SuspendedStatus suspended;
        private SchedulerStatus scheduled;

        public Builder suspended(SuspendedStatus value) {
            suspended = checkNotNull(value);
            updateExist();
            return this;
        }

        public Builder scheduled(SchedulerStatus value) {
            scheduled = checkNotNull(value);
            updateExist();
            return this;
        }

        private void updateExist() {
            boolean wellDefined = suspended != null && scheduled != null;
            if (wellDefined) {
                exist(suspended != SuspendedStatus.UNKNOWN && scheduled != SchedulerStatus.UNKNOWN);
            } else {
                exist(false);
            }
        }

        @Override
        public SchedulerState build() {
            return new SchedulerState(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
