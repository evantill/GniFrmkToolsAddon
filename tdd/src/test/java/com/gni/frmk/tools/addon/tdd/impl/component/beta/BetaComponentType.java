package com.gni.frmk.tools.addon.tdd.impl.component.beta;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentId;
import com.gni.frmk.tools.addon.tdd.api.ComponentState;
import com.gni.frmk.tools.addon.tdd.api.ComponentType;
import com.google.common.collect.ComparisonChain;

/**
 * Created by IntelliJ IDEA.
 * Date: 27/07/11
 * Time: 18:23
 *
 * @author: e03229
 */
public class BetaComponentType implements ComponentType<BetaComponent, BetaComponentId, BetaComponentState> {

    @Override
    public BetaComponent check(Component component) {
        return (BetaComponent) component;
    }

    @Override
    public boolean accept(Component component) {
        return component instanceof BetaComponent;
    }

    @Override
    public BetaComponentId check(ComponentId id) {
        return (BetaComponentId) id;
    }

    @Override
    public boolean accept(ComponentId id) {
        return id instanceof BetaComponentId;
    }

    @Override
    public BetaComponentState check(ComponentState state) {
        return (BetaComponentState) state;
    }

    @Override
    public boolean accept(ComponentState state) {
        return state instanceof BetaComponentState;
    }

    @Override
    public int compareTo(ComponentType o) {
        return ComparisonChain.start()
                              .compare(this.hashCode(), o.hashCode())
                              .result();
    }
}
