package com.gni.frmk.tools.addon.tdd.impl.alpha;

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
public class AlphaComponentType implements ComponentType<AlphaComponent, AlphaComponentId, AlphaComponentState> {

    @Override
    public AlphaComponent check(Component component) {
        return (AlphaComponent) component;
    }

    @Override
    public boolean accept(Component component) {
        return component instanceof AlphaComponent;
    }

    @Override
    public AlphaComponentId check(ComponentId id) {
        return (AlphaComponentId) id;
    }

    @Override
    public boolean accept(ComponentId id) {
        return id instanceof AlphaComponentId;
    }

    @Override
    public AlphaComponentState check(ComponentState state) {
        return (AlphaComponentState) state;
    }

    @Override
    public boolean accept(ComponentState state) {
        return state instanceof AlphaComponentState;
    }

    @Override
    public int compareTo(ComponentType o) {
        return ComparisonChain.start()
                              .compare(this.hashCode(), o.hashCode())
                              .result();
    }
}
