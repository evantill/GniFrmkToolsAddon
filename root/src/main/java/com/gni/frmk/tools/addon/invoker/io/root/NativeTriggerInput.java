package com.gni.frmk.tools.addon.invoker.io.root;

import com.gni.frmk.tools.addon.invoker.api.ServiceInput;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 17:09
 *
 * @author: e03229
 */
public class NativeTriggerInput implements ServiceInput {
    private final boolean applyChangeAcrossCluster;
    private final boolean retrievalSuspend;
    private final boolean processingSuspend;
    private final boolean persistChange;
    private final String[] triggerNameList;

    public NativeTriggerInput(Builder builder) {
        applyChangeAcrossCluster = builder.applyChangeAcrossCluster;
        triggerNameList = builder.triggerNameList;
        retrievalSuspend = builder.retrievalSuspend;
        processingSuspend = builder.processingSuspend;
        persistChange = builder.persistChange;
    }

    public boolean isApplyChangeAcrossCluster() {
        return applyChangeAcrossCluster;
    }

    public boolean isRetrievalSuspend() {
        return retrievalSuspend;
    }

    public boolean isProcessingSuspend() {
        return processingSuspend;
    }

    public boolean isPersistChange() {
        return persistChange;
    }

    public String[] getTriggerNameList() {
        return triggerNameList;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private boolean applyChangeAcrossCluster = false;
        private String[] triggerNameList;
        private boolean retrievalSuspend;
        private boolean processingSuspend;
        private boolean persistChange;

        public Builder applyChangeAcrossCluster(boolean value) {
            applyChangeAcrossCluster = value;
            return this;
        }

        public Builder retrievalSuspend(boolean value) {
            retrievalSuspend = value;
            return this;
        }

        public Builder processingSuspend(boolean value) {
            processingSuspend = value;
            return this;
        }

        public Builder persistChange(boolean value) {
            persistChange = value;
            return this;
        }

        public Builder triggerNames(String... names) {
            triggerNameList = Arrays.copyOf(names, names.length);
            return this;
        }

        public NativeTriggerInput build() {
            return new NativeTriggerInput(this);
        }
    }
}
