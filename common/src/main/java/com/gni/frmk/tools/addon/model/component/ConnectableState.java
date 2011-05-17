package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.BaseComponent.AbstractState;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:47
 *
 * @author: e03229
 */
public final class ConnectableState extends AbstractState<ConnectableState> {

    private ConnectableStatus connected = ConnectableStatus.UNKNOWN;
    private EnableStatus enabled = EnableStatus.UNKNOWN;

    public ConnectableState() {
        super(false);
    }

    public ConnectableState(EnableStatus enabled, ConnectableStatus connected) {
        super(enabled != EnableStatus.UNKNOWN && connected != ConnectableStatus.UNKNOWN);
        this.enabled = enabled;
        this.connected = connected;
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

    @Override
    public int compareTo(ConnectableState other) {
        return ComparisonChain.start()
                              .compare(0, super.compareTo(other))
                              .compare(getEnabled(), other.getEnabled())
                              .compare(getConnected(), other.getConnected())
                              .result();
    }

    @Override
    public boolean isUnknown() {
        return connected==ConnectableStatus.UNKNOWN || enabled==EnableStatus.ENABLED;
    }
}
