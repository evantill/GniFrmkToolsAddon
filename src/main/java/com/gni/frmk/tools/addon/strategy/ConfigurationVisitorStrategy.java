package com.gni.frmk.tools.addon.strategy;

import com.gni.frmk.tools.addon.model.configuration.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:23
 * To change this template use File | Settings | File Templates.
 */
public interface ConfigurationVisitorStrategy {
    void execute(Configuration cnf);
}
