package com.gni.frmk.tools.addon.invoker.io.root;

import com.gni.frmk.tools.addon.invoker.api.ServiceOutput;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 17:21
 *
 * @author: e03229
 */
public class PortInfo implements ServiceOutput, Comparable<PortInfo> {
    private final String key;
    private final String packageName;
    private final boolean primary;
    private final boolean enabled;
    private final boolean suspended;
    private final boolean listening;
    private final String port;
    private final String protocol;
    private final String status;

    private PortInfo(Builder builder) {
        key = builder.key;
        packageName = builder.packageName;
        primary = builder.primary;
        enabled = builder.enabled;
        suspended = builder.suspended;
        listening = builder.listening;
        port = builder.port;
        protocol = builder.protocol;
        status = builder.status;
    }

    @Override
    public int compareTo(PortInfo o) {
        return ComparisonChain.start()
                              .compare(getKey(), o.getKey())
                              .compare(getPackageName(), o.getPackageName())
                              .compare(isPrimary(), o.getKey())
                              .compare(isEnabled(), o.isEnabled())
                              .compare(isSuspended(), o.isSuspended())
                              .compare(isListening(), o.isListening())
                              .compare(getPort(), o.getPort())
                              .compare(getProtocol(), o.getProtocol())
                              .compare(getStatus(), o.getStatus())
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortInfo that = (PortInfo) o;

        return Objects.equal(getKey(), that.getKey())
               && Objects.equal(getPackageName(), that.getPackageName())
               && Objects.equal(isPrimary(), that.isPrimary())
               && Objects.equal(isEnabled(), that.isEnabled())
               && Objects.equal(isSuspended(), that.isSuspended())
               && Objects.equal(isListening(), that.isListening())
               && Objects.equal(getPort(), that.getPort())
               && Objects.equal(getProtocol(), that.getProtocol())
               && Objects.equal(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getKey(), getPackageName(), isPrimary(), isEnabled(), isSuspended(), isListening(), getPort(), getProtocol(), getStatus());
    }

    public String getKey() {
        return key;
    }

    public String getPackageName() {
        return packageName;
    }

    public boolean isPrimary() {
        return primary;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public boolean isListening() {
        return listening;
    }

    public String getPort() {
        return port;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getStatus() {
        return status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String key;
        private String packageName;
        private boolean primary = false;
        private boolean enabled;
        private boolean suspended;
        private boolean listening;
        private String port;
        private String protocol;
        private String status;

        public Builder key(String value) {
            key = checkNotNull(value);
            return this;
        }

        public Builder packageName(String value) {
            packageName = checkNotNull(value);
            return this;
        }

        public Builder primary(Boolean value) {
            primary = Objects.firstNonNull(value, Boolean.FALSE);
            return this;
        }

        public Builder enabled(Boolean value) {
            enabled = checkNotNull(value);
            return this;
        }

        public Builder suspended(Boolean value) {
            suspended = checkNotNull(value);
            return this;
        }

        public Builder listening(Boolean value) {
            listening = checkNotNull(value);
            return this;
        }

        public Builder port(String value) {
            port = checkNotNull(value);
            return this;
        }

        public Builder protocol(String value) {
            protocol = checkNotNull(value);
            return this;
        }

        public Builder status(String value) {
            status = checkNotNull(value);
            return this;
        }

        public PortInfo build() {
            return new PortInfo(this);
        }
    }
}
