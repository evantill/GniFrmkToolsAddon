package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.components.Component.ComponentId;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 11:56
 *
 * @author: e03229
 */
public class StringComponentId implements ComponentId {
    private final String value;

    public StringComponentId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String asString() {
        return value;
    }
}
