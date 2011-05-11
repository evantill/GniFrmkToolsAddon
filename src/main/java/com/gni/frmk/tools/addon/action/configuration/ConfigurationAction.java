package com.gni.frmk.tools.addon.action.configuration;

import com.gni.frmk.tools.addon.api.action.Action;
import com.gni.frmk.tools.addon.api.action.Result;
import com.gni.frmk.tools.addon.model.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 13:35
 *
 * @author: e03229
 */
public abstract class ConfigurationAction<R extends Result> implements Action<R> {

    private final Configuration configuration;

    protected ConfigurationAction(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
