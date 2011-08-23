package com.gni.frmk.tools.addon.tdd.tdd3.impl;

import com.gni.frmk.tools.addon.tdd.tdd3.api.ComponentData;

/**
 * Created by IntelliJ IDEA.
 * Date: 23/08/11
 * Time: 16:13
 *
 * @author: e03229
 */
class SimpleComponentData implements ComponentData<SimpleComponentData> {

    private boolean enabled;
    private String description;

    SimpleComponentData(boolean enabled, String description) {
        this.enabled = enabled;
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public SimpleComponentData save() {
        return new SimpleComponentData(enabled, description);
    }

    @Override
    public void restore(SimpleComponentData saved) {
        enabled = saved.enabled;
        description = saved.description;
    }
}
