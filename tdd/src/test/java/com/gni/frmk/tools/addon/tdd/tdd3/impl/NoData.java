package com.gni.frmk.tools.addon.tdd.tdd3.impl;

import com.gni.frmk.tools.addon.tdd.tdd3.api.ComponentData;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/08/11
 * Time: 16:19
 *
 * @author: e03229
 */
public enum NoData implements ComponentData {
    UNDEFINED;

    @Override
    public ComponentData save() {
        return this;
    }

    @Override
    public void restore(ComponentData saved) {
        throw new IllegalStateException("undefined");
    }

    @Override
    public boolean defined() {
        return false;
    }
}
