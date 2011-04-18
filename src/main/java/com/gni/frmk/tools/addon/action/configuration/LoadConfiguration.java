package com.gni.frmk.tools.addon.action.configuration;

import com.gni.frmk.tools.addon.action.StringAction;
import com.gni.frmk.tools.addon.result.ConfigurationResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 11:46
 *
 * @author: e03229
 */
public class LoadConfiguration extends StringAction<ConfigurationResult> {
    public LoadConfiguration(String configurationId) {
        super(configurationId);
    }

    public String getConfigurationId() {
        return getParameter();
    }
}
