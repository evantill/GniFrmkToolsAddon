package com.gni.frmk.tools.addon;

import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.strategy.ConfigurationVisitorStrategy;

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