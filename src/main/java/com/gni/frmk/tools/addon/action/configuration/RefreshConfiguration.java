package com.gni.frmk.tools.addon.action.configuration;

import com.gni.frmk.tools.addon.action.StringAction;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.result.ConfigurationResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 13:34
 *
 * @author: e03229
 */
public class RefreshConfiguration extends ConfigurationAction<ConfigurationResult> {
    public RefreshConfiguration(Configuration configuration) {
        super(configuration);
    }

}
