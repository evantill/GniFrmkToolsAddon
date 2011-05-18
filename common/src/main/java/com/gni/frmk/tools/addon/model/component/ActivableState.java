package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.BaseComponent.AbstractState;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:10
 *
 * @author: e03229
 */
public class ActivableState extends AbstractState<ActivableState> {

    private ActivableStatus activable;
    private EnableStatus enabled;

    public ActivableState(EnableStatus enabled, ActivableStatus activable) {
        super(enabled != EnableStatus.UNKNOWN && activable != ActivableStatus.UNKNOWN);
        this.enabled = enabled;
        this.activable = activable;
    }

    private ActivableState() {
        super(false);
        enabled = EnableStatus.UNKNOWN;
        activable = ActivableStatus.UNKNOWN;
    }

    public ActivableStatus getActivable() {
        return activable;
    }

    public void setActivable(ActivableStatus activable) {
        this.activable = activable;
    }

    public EnableStatus getEnabled() {
        return enabled;
    }

    public void setEnabled(EnableStatus enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean unknown() {
        return activable == ActivableStatus.UNKNOWN || enabled == EnableStatus.UNKNOWN;
    }
}
