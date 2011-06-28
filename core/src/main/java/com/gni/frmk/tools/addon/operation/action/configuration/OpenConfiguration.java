package com.gni.frmk.tools.addon.operation.action.configuration;

import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.operation.result.ConfigurationResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/06/11
 * Time: 17:41
 *
 * @author: e03229
 */
public class OpenConfiguration implements Action<ConfigurationResult> {

    private final Configuration<?> configuration;

    public OpenConfiguration(Configuration<?> configuration) {
        this.configuration = configuration;
    }

    public Configuration<?> getConfiguration() {
        return configuration;
    }
}
