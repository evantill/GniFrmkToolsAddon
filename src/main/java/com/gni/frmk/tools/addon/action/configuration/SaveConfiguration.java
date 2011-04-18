package com.gni.frmk.tools.addon.action.configuration;

import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.result.ConfigurationResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 11:50
 *
 * @author: e03229
 */
public class SaveConfiguration extends ConfigurationAction<ConfigurationResult> {

    public SaveConfiguration(Configuration configuration) {
        super(configuration);
    }
}
