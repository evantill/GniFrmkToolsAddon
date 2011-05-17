package com.gni.frmk.tools.addon.operation.action.configuration.repository;

import com.gni.frmk.tools.addon.operation.api.Action;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 11:50
 *
 * @author: e03229
 */
public class SaveConfiguration implements Action<SingleResult<Configuration>> {

    private final Configuration configuration;

    public SaveConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
