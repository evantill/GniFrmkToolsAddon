package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.ActivableStatus;
import com.gni.frmk.tools.addon.model.component.BaseComponent.AbstractState;
import com.google.common.collect.ComparisonChain;

/**
* Created by IntelliJ IDEA.
* Date: 17/05/11
* Time: 18:06
*
* @author: e03229
*/
public class TemporaryActivableState extends AbstractState<TemporaryActivableState> {
    private TemporaryStatus temporary;
    private ActivableStatus activable;

    public TemporaryActivableState(TemporaryStatus temporary, ActivableStatus activable) {
        super(activable != ActivableStatus.UNKNOWN);
        this.temporary = temporary;
        this.activable = activable;
    }

    public TemporaryActivableState() {
        super(false);
    }

    public TemporaryStatus getTemporary() {
        return temporary;
    }

    public void setTemporary(TemporaryStatus temporary) {
        this.temporary = temporary;
    }

    public ActivableStatus getActivable() {
        return activable;
    }

    public void setActivable(ActivableStatus activable) {
        this.activable = activable;
    }

    @Override
    public boolean unknown() {
        return temporary == TemporaryStatus.UNKNOWN || activable == ActivableStatus.UNKNOWN;
    }

    @Override
    public int compareTo(TemporaryActivableState other) {
        return ComparisonChain.start()
                              .compare(0, super.compareTo(other))
                              .compare(getTemporary(), other.getTemporary())
                              .compare(getActivable(), other.getActivable())
                              .result();
    }
}
