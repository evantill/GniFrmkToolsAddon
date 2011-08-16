package com.gni.frmk.tools.addon.tdd.component;

import com.gni.frmk.tools.addon.tdd.api.ComponentState;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 20:54
 *
 * @author: e03229
 */
public class SimpleBooleanState implements ComponentState {
    private final Boolean opened;

    public SimpleBooleanState(Boolean opened) {
        this.opened = opened;
    }

    public Boolean getOpened() {
        return opened;
    }
}
