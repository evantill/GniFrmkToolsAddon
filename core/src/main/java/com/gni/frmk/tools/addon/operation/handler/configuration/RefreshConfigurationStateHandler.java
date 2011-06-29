package com.gni.frmk.tools.addon.operation.handler.configuration;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.operation.action.component.RefreshComponentState;
import com.gni.frmk.tools.addon.operation.action.configuration.RefreshConfigurationState;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.Dispatcher;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.ConfigurationResult;

import javax.enterprise.util.TypeLiteral;

/**
 * Created by IntelliJ IDEA.
 * Date: 28/06/11
 * Time: 17:47
 *
 * @author: e03229
 */
public class RefreshConfigurationStateHandler
        implements ActionHandler<RefreshConfigurationState, ConfigurationResult, InvokeContext> {

    private final TypeLiteral<RefreshConfigurationState> TYPE_LITERAL = new TypeLiteral<RefreshConfigurationState>() {
    };

    @Override
    public TypeLiteral<RefreshConfigurationState> getActionType() {
        return TYPE_LITERAL;
    }

    @Override
    public ConfigurationResult execute(RefreshConfigurationState action, InvokeContext context) throws ActionException {
        Configuration<?> configuration = action.getConfiguration();
        for (ComponentConfiguration cc : configuration.getComponentConfigurations()) {
            refreshComponentState(action, context.getDispatcher(), cc);
        }
        return ConfigurationResult.newInstance(configuration);
    }

    private <CC extends ComponentConfiguration<CC, T, C, I, S>,
            T extends ComponentType<T, C, I, S, ?>,
            C extends Component<C, T, I, S, ?>,
            I extends ComponentId<I>,
            S extends ComponentState<S>>
    CC refreshComponentState(RefreshConfigurationState action, Dispatcher dispatcher, CC configuration) throws ActionException {
        C component = configuration.getComponent();
        C updatedComponent = dispatcher.executeFromAction(action, RefreshComponentState.build(component)).getValue();
        configuration.setComponent(updatedComponent);
        return configuration;
    }
}
