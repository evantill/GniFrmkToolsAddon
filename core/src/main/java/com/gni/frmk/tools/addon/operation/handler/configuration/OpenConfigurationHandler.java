package com.gni.frmk.tools.addon.operation.handler.configuration;

import com.gni.frmk.tools.addon.model.component.ComponentStateType;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.operation.action.configuration.ChangeConfigurationState;
import com.gni.frmk.tools.addon.operation.action.configuration.OpenConfiguration;
import com.gni.frmk.tools.addon.operation.action.configuration.RefreshConfigurationState;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.ConfigurationResult;

import javax.enterprise.util.TypeLiteral;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/06/11
 * Time: 17:41
 *
 * @author: e03229
 */
public class OpenConfigurationHandler implements ActionHandler<OpenConfiguration, ConfigurationResult, InvokeContext> {

    TypeLiteral<OpenConfiguration> TYPE_LITERAL = new TypeLiteral<OpenConfiguration>() {
    };

    @Override
    public TypeLiteral<OpenConfiguration> getActionType() {
        return TYPE_LITERAL;
    }

    @Override
    public ConfigurationResult execute(OpenConfiguration action, InvokeContext context) throws ActionException {
        try {
            Configuration<?> configuration = action.getConfiguration();
            //refresh CURRENT state
            configuration = context.getDispatcher().execute(new RefreshConfigurationState(configuration)).getValue();
            //define LAST state to CURRENT state
            for (ComponentConfiguration<?, ?, ?, ?> cc : configuration.getComponentConfigurations()) {
                cc.updateLastState();
            }
            //open components
            configuration = context.getDispatcher()
                                   .execute(new ChangeConfigurationState(configuration, ComponentStateType.OPEN))
                                   .getValue();
            return ConfigurationResult.newInstance(configuration);
        } catch (DispatchException cause) {
            throw new ActionException(action, cause);
        }
    }
}
