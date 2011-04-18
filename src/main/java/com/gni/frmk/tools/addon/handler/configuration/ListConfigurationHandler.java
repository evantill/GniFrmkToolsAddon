package com.gni.frmk.tools.addon.handler.configuration;

import com.gni.frmk.tools.addon.action.configuration.ListConfiguration;
import com.gni.frmk.tools.addon.api.action.ActionHandler;
import com.gni.frmk.tools.addon.dispatch.wm.invoke.api.InvokeContext;
import com.gni.frmk.tools.addon.handler.configuration.repository.ConfigurationRepository;
import com.gni.frmk.tools.addon.result.SetResult;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 11:56
 *
 * @author: e03229
 */
public class ListConfigurationHandler
        implements ActionHandler<ListConfiguration, SetResult<String>, InvokeContext> {

    private final ConfigurationRepository repository;

    public ListConfigurationHandler(ConfigurationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Class<ListConfiguration> getActionType() {
        return ListConfiguration.class;
    }

    @Override
    public SetResult<String> execute(ListConfiguration action, InvokeContext context) {
        return new SetResult<String>(repository.getConfigurationList());
    }
}
