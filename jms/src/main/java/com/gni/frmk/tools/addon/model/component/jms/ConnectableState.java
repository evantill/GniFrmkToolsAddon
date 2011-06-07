package com.gni.frmk.tools.addon.model.component.jms;

import com.gni.frmk.tools.addon.model.component.EnableStatus;
import com.gni.frmk.tools.addon.model.component.base.BaseComponentState;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:47
 *
 * @author: e03229
 */
@XmlRootElement
@XmlType(propOrder = {
        "enabled",
        "connected"
})
public final class ConnectableState extends BaseComponentState<ConnectableState> {

    private ConnectableStatus connected = ConnectableStatus.UNKNOWN;
    private EnableStatus enabled = EnableStatus.UNKNOWN;

    protected ConnectableState() {
        super();
    }

    public ConnectableState(Builder builder) {
        super(builder);
        this.enabled = builder.enabled;
        this.connected = builder.connected;
    }

    public EnableStatus getEnabled() {
        return enabled;
    }

    public void setEnabled(EnableStatus enabled) {
        this.enabled = enabled;
    }

    public ConnectableStatus getConnected() {
        return connected;
    }

    public void setConnected(ConnectableStatus connected) {
        this.connected = connected;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static ConnectableState build(EnableStatus enabled, ConnectableStatus connected) {
        return builder().enable(enabled).connected(connected).validate().build();
    }

    @Override
    protected ComparisonChain extendedCompareTo(ComparisonChain chain, ConnectableState other) {
        return chain.compare(getEnabled(), other.getEnabled()).compare(getConnected(), other.getConnected());
    }

    @Override
    protected boolean extendedEquals(ConnectableState other) {
        return Objects.equal(enabled, other.enabled)
               && Objects.equal(connected, other.connected);
    }

    @Override
    protected Object[] extendedHashCode() {
        return new Object[]{enabled,connected};
    }

    @Override
    public boolean unknown() {
        return connected == ConnectableStatus.UNKNOWN || enabled == EnableStatus.ENABLED;
    }

    @XmlTransient
    public static final class Builder extends BaseComponentState.Builder<Builder, ConnectableState> {
        private ConnectableStatus connected;
        private EnableStatus enabled;

        public Builder enable(EnableStatus value) {
            this.enabled = checkNotNull(value);
            updateExist();
            return this;
        }

        public Builder connected(ConnectableStatus value) {
            this.connected = checkNotNull(value);
            updateExist();
            return this;
        }

        private void updateExist() {
            boolean unknown = connected == ConnectableStatus.UNKNOWN || enabled == EnableStatus.UNKNOWN;
            exist(!unknown);
        }

        @Override
        public ConnectableState build() {
            return new ConnectableState(this);
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Builder validate() {
            checkNotNull(enabled);
            checkNotNull(connected);
            return super.validate();
        }
    }

}
