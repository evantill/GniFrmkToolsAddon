package com.gni.frmk.tools.addon.invoker.io.jms;

import com.gni.frmk.tools.addon.invoker.api.ServiceInput;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 17:09
 *
 * @author: e03229
 */
public class JmsTriggerInput implements ServiceInput {
    private final boolean applyChangeAcrossCluster;
    private final String[] triggerNameList;

    public JmsTriggerInput(Builder builder) {
        applyChangeAcrossCluster = builder.applyChangeAcrossCluster;
        triggerNameList = builder.triggerNameList;
    }

    public boolean isApplyChangeAcrossCluster() {
        return applyChangeAcrossCluster;
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

        public Builder applyChangeAcrossCluster(boolean value) {
            applyChangeAcrossCluster = value;
            return this;
        }

        public Builder triggerNames(String... names) {
            triggerNameList = Arrays.copyOf(names,names.length);
            return this;
        }

        public JmsTriggerInput build() {
            return new JmsTriggerInput(this);
        }
    }
}
