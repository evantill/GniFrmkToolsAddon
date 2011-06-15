package com.gni.frmk.tools.addon.operation.handler.configuration.repository;

import com.gni.frmk.tools.addon.model.configuration.ConfigurationId;
import com.gni.frmk.tools.addon.operation.action.configuration.repository.DeleteConfiguration;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.repository.ConfigurationRepository;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 11:13
 *
 * @author: e03229
 */
public class DeleteConfigurationHandler implements ActionHandler<DeleteConfiguration, SingleResult<Boolean>, InvokeContext> {

    private final ConfigurationRepository repository;

    public DeleteConfigurationHandler(ConfigurationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Class<DeleteConfiguration> getActionType() {
        return DeleteConfiguration.class;
    }

    @Override
    public SingleResult<Boolean> execute(DeleteConfiguration action, InvokeContext context) throws ActionException {
        ConfigurationId configurationId = action.getConfigurationId();
        boolean deleted = repository.deleteConfiguration(configurationId);
        return new SingleResult<Boolean>(deleted);
    }
}
