package com.gni.frmk.tools.addon.invoker.io.jms;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 16:43
 *
 * @author: e03229
 */
public class JmsTriggerInfo implements Comparable<JmsTriggerInfo> {
    private final String nodeName;
    private final String packageName;
    private final boolean enabled;
    private final boolean running;
    private final int state;

    private JmsTriggerInfo(Builder builder) {
        nodeName = builder.nodeName;
        packageName = builder.packageName;
        enabled = builder.enabled;
        running = builder.running;
        state = builder.state;
    }

    public String getNodeName() {
        return nodeName;
    }

    public String getPackageName() {
        return packageName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isRunning() {
        return running;
    }

    public int getState() {
        return state;
    }

    @Override
    public int compareTo(JmsTriggerInfo o) {
        return ComparisonChain.start()
                              .compare(getNodeName(), o.getNodeName())
                              .compare(getPackageName(), o.getPackageName())
                              .compare(isEnabled(), o.isEnabled())
                              .compare(isRunning(), o.isRunning())
                              .compare(getState(), o.getState())
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmsTriggerInfo that = (JmsTriggerInfo) o;

        return
                Objects.equal(getNodeName(), that.getNodeName())
                && Objects.equal(getPackageName(), that.getPackageName())
                && Objects.equal(isEnabled(), that.isEnabled())
                && Objects.equal(isRunning(), that.isRunning())
                && Objects.equal(getState(), that.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNodeName(), getPackageName(), isEnabled(), isRunning(), getState());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String nodeName;
        private String packageName;
        private boolean enabled;
        private boolean running;
        private int state;

        public Builder nodeName(String value) {
            nodeName = checkNotNull(value);
            return this;
        }

        public Builder packageName(String value) {
            packageName = checkNotNull(value);
            return this;
        }

        public Builder enabled(Boolean value) {
            enabled = checkNotNull(value);
            return this;
        }

        public Builder running(Boolean value) {
            running = checkNotNull(value);
            return this;
        }

        public Builder state(Integer value) {
            state = checkNotNull(value);
            return this;
        }

        public JmsTriggerInfo build() {
            return new JmsTriggerInfo(this);
        }
    }
}
