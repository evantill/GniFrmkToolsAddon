package com.gni.frmk.tools.addon.operation;

import com.gni.frmk.tools.addon.configuration.Configuration;
import com.gni.frmk.tools.addon.operation.strategy.ConfigurationVisitorStrategy;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/10/10
 * Time: 11:23
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurationVisitorContext {

    private final ConfigurationVisitorStrategy strategy;

    public ConfigurationVisitorContext(ConfigurationVisitorStrategy strategy) {
        this.strategy = strategy;
    }

    void executeStrategy(Configuration cnf) {
        strategy.execute(cnf);
    }
}
