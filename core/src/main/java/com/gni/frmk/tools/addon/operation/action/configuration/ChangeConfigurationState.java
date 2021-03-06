package com.gni.frmk.tools.addon.operation.action.configuration;

import com.gni.frmk.tools.addon.model.component.ComponentStateType;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.ConfigurationResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/06/11
 * Time: 17:46
 *
 * @author: e03229
 */
public class ChangeConfigurationState implements Action<ConfigurationResult> {
    private final Configuration<?> configuration;
    private final ComponentStateType from;

    public ChangeConfigurationState(Configuration<?> configuration, ComponentStateType from) {
        this.configuration = configuration;
        this.from = from;
    }

    public Configuration<?> getConfiguration() {
        return configuration;
    }

    public ComponentStateType getFrom() {
        return from;
    }
}
