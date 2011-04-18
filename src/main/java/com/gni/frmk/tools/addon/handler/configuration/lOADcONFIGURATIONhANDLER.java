package com.gni.frmk.tools.addon.handler.configuration;

import com.gni.frmk.tools.addon.action.configuration.LoadConfiguration;
import com.gni.frmk.tools.addon.api.action.ActionException;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.api.action.ExecutionContext;
import com.gni.frmk.tools.addon.handler.configuration.repository.ConfigurationRepository;
import com.gni.frmk.tools.addon.result.ConfigurationResult;

/**
 * First implementation : only default live configuration file is managed
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 11:56
 *
 * @author: e03229
 */
public class LoadConfigurationHandler
        implements ActionHandler<LoadConfiguration, ConfigurationResult, ExecutionContext> {

    private final ConfigurationRepository repository;

    public LoadConfigurationHandler(ConfigurationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Class<LoadConfiguration> getActionType() {
        return LoadConfiguration.class;
    }

    /**
     * First implementation : only default live configuration file is managed
     *
     * @param action  the configuration id must be "default"
     * @param context execution context
     * @return the configuration uptodate.
     * @throws ActionException
     */
    @Override
    public ConfigurationResult execute(LoadConfiguration action, ExecutionContext context) {
        return new ConfigurationResult(repository.loadConfiguration(action.getConfigurationId()));
    }
}
