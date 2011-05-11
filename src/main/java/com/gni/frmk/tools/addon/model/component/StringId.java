package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.BaseComponent.AbstractId;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 11:56
 *
 * @author: e03229
 */
public class StringId extends AbstractId {

    private String value;

    public StringId(String value) {
        this.value = value;
    }

    public StringId() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
