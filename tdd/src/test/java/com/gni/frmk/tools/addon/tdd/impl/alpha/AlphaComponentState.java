package com.gni.frmk.tools.addon.tdd.impl.alpha;

import com.gni.frmk.tools.addon.tdd.api.ComponentState;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 18:25
 *
 * @author: e03229
 */
public class AlphaComponentState implements ComponentState<AlphaComponentState> {

    private AlphaComponentType componentType;
    private AlphaComponentId componentId;

    public AlphaComponentState(AlphaComponentType componentType, AlphaComponentId componentId) {
        this.componentType = componentType;
        this.componentId = componentId;
    }

    @Override
    public AlphaComponentType getComponentType() {
        return componentType;
    }

    @Override
    public AlphaComponentId getComponentId() {
        return componentId;
    }

    @Override
    public int compareTo(AlphaComponentState o) {
        return ComparisonChain.start()
                              .compare(this.hashCode(), o.hashCode())
                              .result();
    }
}
