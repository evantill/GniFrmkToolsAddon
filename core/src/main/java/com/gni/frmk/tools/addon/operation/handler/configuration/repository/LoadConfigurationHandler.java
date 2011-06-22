package com.gni.frmk.tools.addon.operation.handler.configuration.repository;

import com.gni.frmk.tools.addon.operation.action.configuration.repository.LoadConfiguration;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.ExecutionContext;
import com.gni.frmk.tools.addon.operation.result.ConfigurationResult;
import com.gni.frmk.tools.addon.repository.ConfigurationRepository;

import javax.enterprise.util.TypeLiteral;

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

    private static final TypeLiteral<LoadConfiguration> TYPE_LITERAL = new TypeLiteral<LoadConfiguration>() {};
    private final ConfigurationRepository repository;

    public LoadConfigurationHandler(ConfigurationRepository repository) {
        this.repository = repository;
    }

    @Override
    public TypeLiteral<LoadConfiguration> getActionType() {
        return TYPE_LITERAL;
    }

    /**
     * First implementation : only default live configuration file is managed
     *
     * @param action  the configuration id must be "default"
     * @param context execution context
     * @return the configuration uptodate.
     * @throws com.gni.frmk.tools.addon.operation.api.ActionException
     */
    @Override
    public ConfigurationResult execute(LoadConfiguration action, ExecutionContext context) {
        return new ConfigurationResult(repository.loadConfiguration(action.getConfigurationId()));
    }
}
