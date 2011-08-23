package com.gni.frmk.tools.addon.tdd.tdd3.impl;

import com.gni.frmk.tools.addon.tdd.tdd3.api.ComponentId;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/08/11
 * Time: 16:11
 *
 * @author: e03229
 */
public class IntegerComponentId implements ComponentId {
    private final int value;

    public IntegerComponentId(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
