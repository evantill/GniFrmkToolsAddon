package com.gni.frmk.tools.addon.invoker.io.root;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 19:26
 *
 * @author: e03229
 */
public class NativeTriggerInfo implements Comparable<NativeTriggerInfo> {
    public static enum NativeTriggerState {
        PERMANENT_ACTIVE(true, true),
        PERMANENT_INACTIVE(true, false),
        TEMPORARY_ACTIVE(false, true),
        TEMPORARY_INACTIVE(false, false);

        private final boolean permanent;
        private final boolean active;

        NativeTriggerState(boolean permanent, boolean active) {
            this.permanent = permanent;
            this.active = active;
        }

        public boolean isPermanent() {
            return permanent;
        }

        public boolean isActive() {
            return active;
        }

        public static NativeTriggerState decode(boolean permanent, boolean active) {
            if (permanent) {
                if (active) {
                    return PERMANENT_ACTIVE;
                } else {
                    return PERMANENT_INACTIVE;
                }
            } else {
                if (active) {
                    return TEMPORARY_ACTIVE;
                } else {
                    return TEMPORARY_INACTIVE;
                }
            }
        }

    }

    private final String name;
    private final boolean executeEnabled;
    private final NativeTriggerState retrieval;
    private final NativeTriggerState processing;

    @Override
    public int compareTo(NativeTriggerInfo o) {
        return ComparisonChain.start()
                              .compare(getName(), o.getName())
                              .compare(isExecuteEnabled(), o.isExecuteEnabled())
                              .compare(getRetrieval(), o.getRetrieval())
                              .compare(getProcessing(), o.getProcessing())
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NativeTriggerInfo that = (NativeTriggerInfo) o;

        return Objects.equal(getName(), that.getName())
               && Objects.equal(isExecuteEnabled(), that.isExecuteEnabled())
               && Objects.equal(getRetrieval(), that.getRetrieval())
               && Objects.equal(getProcessing(), that.getProcessing());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), isExecuteEnabled(), getRetrieval(), getProcessing());
    }

    public String getName() {
        return name;
    }

    public boolean isExecuteEnabled() {
        return executeEnabled;
    }

    public NativeTriggerState getRetrieval() {
        return retrieval;
    }

    public NativeTriggerState getProcessing() {
        return processing;
    }

    private NativeTriggerInfo(Builder builder) {
        name = builder.name;
        executeEnabled = builder.executeEnabled;
        retrieval = builder.retrieval;
        processing = builder.processing;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private boolean executeEnabled;
        private NativeTriggerState retrieval;
        private NativeTriggerState processing;

        public Builder name(String value) {
            name = checkNotNull(value);
            return this;
        }

        public Builder executeEnabled(Boolean value) {
            executeEnabled = checkNotNull(executeEnabled);
            return this;
        }

        public Builder retrieval(NativeTriggerState value) {
            retrieval = checkNotNull(value);
            return this;
        }

        public Builder processing(NativeTriggerState value) {
            processing = checkNotNull(value);
            return this;
        }

        public NativeTriggerInfo build() {
            return new NativeTriggerInfo(this);
        }
    }

}
