package com.gni.frmk.tools.addon.action.configuration;

import com.gni.frmk.tools.addon.model.configuration.ImmutableConfiguration;
import com.gni.frmk.tools.addon.result.ConfigurationResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 11:50
 *
 * @author: e03229
 */
public class SaveConfiguration extends ConfigurationAction<ConfigurationResult> {

    public SaveConfiguration(ImmutableConfiguration configuration) {
        super(configuration);
    }
}
