package com.gni.frmk.tools.addon.operation.action.configuration.repository;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.model.configuration.ConfigurationId;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 11:13
 *
 * @author: e03229
 */
public class DeleteConfiguration implements Action<SingleResult<Boolean>> {
    private final ConfigurationId configurationId;

    public DeleteConfiguration(ConfigurationId configurationId) {
        this.configurationId = configurationId;
    }

    public ConfigurationId getConfigurationId() {
        return configurationId;
    }
}
