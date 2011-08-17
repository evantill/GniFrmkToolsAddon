package com.gni.frmk.tools.addon.tdd.alpha;

import com.gni.frmk.tools.addon.tdd.api.ComponentId;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/08/11
 * Time: 17:53
 *
 * @author: e03229
 */
public abstract class AlphaId implements ComponentId {
    int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
