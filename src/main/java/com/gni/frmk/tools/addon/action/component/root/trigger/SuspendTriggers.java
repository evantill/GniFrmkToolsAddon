package com.gni.frmk.tools.addon.action.component.root.trigger;

import com.gni.frmk.tools.addon.result.NoResult;
import com.google.common.collect.Sets;
import com.gni.frmk.tools.addon.api.action.Action;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Collections.unmodifiableSet;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/03/11
 * Time: 09:37
 *
 * @author: e03229
 */
public class SuspendTriggers implements Action<NoResult> {
    private final Set<String> triggerNames;
    private final boolean applyChangeAcrossCluster;
    private final boolean persistChange;
    private final boolean retrievalSuspend;
    private final boolean processingSuspend;

    private SuspendTriggers(Builder builder) {
        this.triggerNames = unmodifiableSet(builder.triggerNames);
        this.applyChangeAcrossCluster = builder.applyChangeAcrossCluster;
        this.persistChange = builder.persistChange;
        this.retrievalSuspend = builder.retrievalSuspend;
        this.processingSuspend = builder.processingSuspend;
    }

    public Set<String> getTriggerNames() {
        return triggerNames;
    }

    public boolean isApplyChangeAcrossCluster() {
        return applyChangeAcrossCluster;
    }

    public boolean isPersistChange() {
        return persistChange;
    }

    public boolean isRetrievalSuspend() {
        return retrievalSuspend;
    }

    public boolean isProcessingSuspend() {
        return processingSuspend;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Set<String> triggerNames = Sets.newHashSet();
        private boolean applyChangeAcrossCluster = false;
        private boolean persistChange;
        private boolean retrievalSuspend;
        private boolean processingSuspend;

        public Builder addTriggerName(String name) {
            triggerNames.add(checkNotNull(name));
            return this;
        }

        public Builder applyChangeAcrossCluster(boolean applyChangeAcrossCluster) {
            this.applyChangeAcrossCluster = applyChangeAcrossCluster;
            return this;
        }

        public Builder persistChange(boolean persistChange) {
            this.persistChange = persistChange;
            return this;
        }

        public Builder suspendRetrieval(boolean retrievalSuspend) {
            this.retrievalSuspend = retrievalSuspend;
            return this;
        }

        public Builder suspendProcessing(boolean processingSuspend) {
            this.processingSuspend = processingSuspend;
            return this;
        }

        public SuspendTriggers build() {
            return new SuspendTriggers(this);
        }
    }
}
