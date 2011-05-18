package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.BaseComponent.AbstractState;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 11:55
 *
 * @author: e03229
 */
public final class EnableState extends AbstractState<EnableState> {

    private EnableStatus enabled = EnableStatus.UNKNOWN;

    public EnableState() {
        super(false);
    }

    public EnableState(EnableStatus enabled) {
        super(enabled != EnableStatus.UNKNOWN);
        this.enabled = enabled;
    }

    public EnableStatus getEnabled() {
        return enabled;
    }

    public void setEnabled(EnableStatus enabled) {
        this.enabled = enabled;
    }

    @Override
    public int compareTo(EnableState other) {
        return ComparisonChain.start()
                              .compare(0, super.compareTo(other))
                              .compare(getEnabled(), other.getEnabled())
                              .result();
    }

    @Override
    public boolean unknown() {
        return enabled==EnableStatus.UNKNOWN;
    }
}
