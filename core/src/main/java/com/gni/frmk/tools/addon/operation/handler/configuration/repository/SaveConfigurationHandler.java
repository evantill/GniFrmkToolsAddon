package com.gni.frmk.tools.addon.operation.handler.configuration.repository;

import com.gni.frmk.tools.addon.operation.action.configuration.repository.SaveConfiguration;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.result.ConfigurationResult;
import com.gni.frmk.tools.addon.repository.ConfigurationRepository;

import javax.enterprise.util.TypeLiteral;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 11:56
 *
 * @author: e03229
 */
public class SaveConfigurationHandler
        implements ActionHandler<SaveConfiguration, ConfigurationResult, ExecutionContext> {

    private static final TypeLiteral<SaveConfiguration> TYPE_LITERAL = new TypeLiteral<SaveConfiguration>() {};
    private final ConfigurationRepository repository;

    public SaveConfigurationHandler(ConfigurationRepository repository) {
        this.repository = repository;
    }

    @Override
    public TypeLiteral<SaveConfiguration> getActionType() {
        return TYPE_LITERAL;
    }

    @Override
    public ConfigurationResult execute(SaveConfiguration action, ExecutionContext context) throws ActionException {
        repository.saveConfiguration(action.getConfiguration());
        return new ConfigurationResult(action.getConfiguration());
    }
}
