package com.gni.frmk.tools.addon.operation.handler.configuration.repository;

import com.gni.frmk.tools.addon.model.configuration.ConfigurationId;
import com.gni.frmk.tools.addon.operation.action.configuration.repository.ListConfiguration;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.SetResult;
import com.gni.frmk.tools.addon.repository.ConfigurationRepository;

import javax.enterprise.util.TypeLiteral;

/**
 * Created by IntelliJ IDEA.
 * Date: 11/04/11
 * Time: 11:56
 *
 * @author: e03229
 */
public class ListConfigurationHandler
        implements ActionHandler<ListConfiguration, SetResult<ConfigurationId>, InvokeContext> {

    private static final TypeLiteral<ListConfiguration> TYPE_LITERAL = new TypeLiteral<ListConfiguration>() {};
    private final ConfigurationRepository repository;

    public ListConfigurationHandler(ConfigurationRepository repository) {
        this.repository = repository;
    }

    @Override
    public TypeLiteral<ListConfiguration> getActionType() {
        return TYPE_LITERAL;
    }

    @Override
    public SetResult<ConfigurationId> execute(ListConfiguration action, InvokeContext context) {
        return SetResult.newInstance(repository.getConfigurationList());
    }
}
