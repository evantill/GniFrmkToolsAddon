package com.gni.frmk.tools.addon.invoker.io.jms;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 15:36
 *
 * @author: e03229
 */
public class JmsAliasInfo implements Comparable<JmsAliasInfo> {

    private final String aliasName;
    private final String description;
    private final boolean enabled;
    private final boolean connected;

    public JmsAliasInfo(Builder builder) {
        this.aliasName = builder.aliasName;
        this.description = builder.description;
        this.enabled = builder.enabled;
        this.connected = builder.connected;
    }

    public String getAliasName() {
        return aliasName;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isConnected() {
        return connected;
    }

    @Override
    public int compareTo(JmsAliasInfo o) {
        return ComparisonChain.start()
                              .compare(getAliasName(), o.getAliasName())
                              .compare(getDescription(), o.getDescription())
                              .compare(isEnabled(), o.isEnabled())
                              .compare(isConnected(), o.isConnected())
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmsAliasInfo that = (JmsAliasInfo) o;

        return Objects.equal(getAliasName(), that.getAliasName())
               && Objects.equal(getDescription(), that.getDescription())
               && Objects.equal(isEnabled(), that.isEnabled())
               && Objects.equal(isConnected(), that.isConnected());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getAliasName(), getDescription(), isEnabled(), isConnected());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String aliasName;
        private String description;
        private boolean enabled;
        private boolean connected;

        public Builder aliasName(String value) {
            aliasName = checkNotNull(value);
            return this;
        }

        public Builder description(String value) {
            description = Objects.firstNonNull(value, "");
            return this;
        }

        public Builder enabled(boolean value) {
            enabled = value;
            return this;
        }

        public Builder connected(boolean value) {
            connected = value;
            return this;
        }

        public JmsAliasInfo build() {
            return new JmsAliasInfo(this);
        }
    }
}
