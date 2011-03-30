package com.gni.frmk.tools.addon.configuration.component;

import com.gni.frmk.tools.addon.configuration.component.ComponentDetail.Value;

/**
* Created by IntelliJ IDEA.
* Date: 17/03/11
* Time: 20:32
*
* @author: e03229
*/
public interface ComponentDetail<T extends Value> {

    String getKey();

    Value getValue();

    public interface Value {
        String asString();
    }
}
