package com.gni.frmk.tools.addon.operation.action.configuration.repository;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationId;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 11:46
 *
 * @author: e03229
 */
public class LoadConfiguration implements Action<SingleResult<Configuration>> {
    private final ConfigurationId configurationId;

    public LoadConfiguration(ConfigurationId configurationId) {
        this.configurationId = configurationId;
    }

    public ConfigurationId getConfigurationId() {
        return configurationId;
    }
}
