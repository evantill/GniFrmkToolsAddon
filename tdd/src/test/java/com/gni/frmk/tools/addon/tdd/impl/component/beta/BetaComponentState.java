package com.gni.frmk.tools.addon.tdd.impl.component.beta;

import com.gni.frmk.tools.addon.tdd.api.ComponentState;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 18:25
 *
 * @author: e03229
 */
public class BetaComponentState implements ComponentState<BetaComponentState> {

    private BetaComponentType componentType;
    private BetaComponentId componentId;

    public BetaComponentState(BetaComponentType componentType, BetaComponentId componentId) {
        this.componentType = componentType;
        this.componentId = componentId;
    }

    @Override
    public BetaComponentType getComponentType() {
        return componentType;
    }

    @Override
    public BetaComponentId getComponentId() {
        return componentId;
    }

    @Override
    public int compareTo(BetaComponentState o) {
        return ComparisonChain.start()
                              .compare(this.hashCode(), o.hashCode())
                              .result();
    }
}
