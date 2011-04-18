package com.gni.frmk.tools.addon.handler.configuration;

import com.gni.frmk.tools.addon.action.configuration.SaveConfiguration;
import com.gni.frmk.tools.addon.api.action.ActionException;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.api.action.ExecutionContext;
import com.gni.frmk.tools.addon.handler.configuration.repository.ConfigurationRepository;
import com.gni.frmk.tools.addon.result.ConfigurationResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 11:56
 *
 * @author: e03229
 */
public class SaveConfigurationHandler
        implements ActionHandler<SaveConfiguration, ConfigurationResult, ExecutionContext> {

    private final ConfigurationRepository repository;

    public SaveConfigurationHandler(ConfigurationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Class<SaveConfiguration> getActionType() {
        return SaveConfiguration.class;
    }

    @Override
    public ConfigurationResult execute(SaveConfiguration action, ExecutionContext context) throws ActionException {
        repository.saveConfiguration(action.getConfiguration());
        return new ConfigurationResult(action.getConfiguration());
    }
}
