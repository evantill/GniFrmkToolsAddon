package com.gni.frmk.tools.addon.operation.result;

import com.gni.frmk.tools.addon.model.configuration.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 11:48
 *
 * @author: e03229
 */
public class ConfigurationResult extends SingleResult<Configuration<?>> {

    public ConfigurationResult(Configuration<?> value) {
        super(value);
    }

    public static ConfigurationResult newInstance(Configuration<?> value) {
        return new ConfigurationResult(value);
    }
}
