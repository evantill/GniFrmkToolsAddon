package com.gni.frmk.tools.addon.tdd.api;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentId;
import com.gni.frmk.tools.addon.tdd.api.ComponentType;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 19:01
 *
 * @author: e03229
 */
public interface CommandContext {
    Component findComponent(ComponentType type, ComponentId id);
}
