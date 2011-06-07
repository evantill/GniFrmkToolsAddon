package com.gni.frmk.tools.addon.invoker.io.art;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 25/05/11
 * Time: 12:14
 *
 * @author: e03229
 */
public final class AdapterConnectionInfo implements Comparable<AdapterConnectionInfo> {
    public static enum ConnectionState {
        ENABLED, DISABLED
    }

    private final String adapterType;
    private final String connectionAlias;
    private final String packageName;
    private final ConnectionState connectionState;

    public AdapterConnectionInfo(Builder builder) {
        adapterType = builder.adapterType;
        connectionAlias = builder.connectionAlias;
        packageName = builder.packageName;
        connectionState = builder.connectionState;
    }

    public String getConnectionAlias() {
        return connectionAlias;
    }

    public String getPackageName() {
        return packageName;
    }

    public ConnectionState getConnectionState() {
        return connectionState;
    }

    public String getAdapterType() {
        return adapterType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private  String adapterType;
        private String connectionAlias;
        private String packageName;
        private ConnectionState connectionState;

        public Builder adapterType(String value) {
            adapterType = Preconditions.checkNotNull(value);
            return this;
        }

        public Builder alias(String value) {
            connectionAlias = Preconditions.checkNotNull(value);
            return this;
        }

        public Builder packageName(String value) {
            packageName = Preconditions.checkNotNull(value);
            return this;
        }

        public Builder connectionState(String value) {
            connectionState = ConnectionState.valueOf(value.toUpperCase());
            return this;
        }

        public AdapterConnectionInfo build() {
            return new AdapterConnectionInfo(this);
        }
    }

    @Override
    public int compareTo(AdapterConnectionInfo o) {
        return ComparisonChain.start()
                              .compare(getAdapterType(), o.getAdapterType())
                              .compare(getConnectionAlias(), o.getConnectionAlias())
                              .compare(getPackageName(), o.getPackageName())
                              .compare(getConnectionState(), o.getConnectionState())
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdapterConnectionInfo that = (AdapterConnectionInfo) o;

        return Objects.equal(getAdapterType(), that.getAdapterType())
               && Objects.equal(getConnectionAlias(), that.getConnectionAlias())
               && Objects.equal(getPackageName(), that.getPackageName())
               && Objects.equal(getConnectionState(), that.getConnectionState());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getAdapterType(), getConnectionAlias(), getPackageName(), getConnectionState());
    }
}
