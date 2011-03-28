package com.gni.frmk.tools.addon.configuration;

import com.gni.frmk.tools.addon.configuration.components.Component;
import com.gni.frmk.tools.addon.configuration.components.ComponentState;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/03/11
 * Time: 10:21
 *
 * @author: e03229
 */
public class ComponentConfiguration {

    public static enum ComponentStateContext {
        OPEN,CLOSE,CURRENT
    }

    private Component component;
    private Map<ComponentStateContext, ComponentState> states;
}
