package com.gni.frmk.tools.addon.result;

import com.gni.frmk.tools.addon.api.action.ActionException;
import com.gni.frmk.tools.addon.model.configuration.Configuration;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 11:48
 *
 * @author: e03229
 */
public class ConfigurationResult extends SingleResult<Configuration> {

    public ConfigurationResult(Configuration value) {
        super(value);
    }
}
