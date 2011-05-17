package com.gni.frmk.tools.addon.operation.action.configuration.server;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 11:04
 *
 * @author: e03229
 */
public class RefreshConfigurationStatus implements Action<SingleResult<Configuration>> {
    private final Configuration configuration;

    public RefreshConfigurationStatus(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}

