package com.gni.frmk.tools.addon.configuration.components;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 11:56
 *
 * @author: e03229
 */
public class StringId implements ComponentId {
    private final String value;

    public StringId(String value) {
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
