package com.gni.frmk.tools.addon.operation.handler.configuration;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentStateType;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.model.configuration.ComponentConfiguration;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.operation.action.component.ChangeComponentState;
import com.gni.frmk.tools.addon.operation.action.configuration.ChangeConfigurationState;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
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
public class ChangeConfigurationStateHandler
        implements ActionHandler<ChangeConfigurationState, ConfigurationResult, InvokeContext> {

    private final TypeLiteral<ChangeConfigurationState> TYPE_LITERAL = new TypeLiteral<ChangeConfigurationState>() {
    };

    @Override
    public TypeLiteral<ChangeConfigurationState> getActionType() {
        return TYPE_LITERAL;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public ConfigurationResult execute(ChangeConfigurationState action, InvokeContext context) throws ActionException {
        Configuration<?> configuration = action.getConfiguration();
        ComponentStateType fromState = action.getFrom();
        //TODO mise en place de la strategie de parsing
        for (ComponentConfiguration cc : configuration.getComponentConfigurations()) {
            try {
                changeComponentState(context.getDispatcher(), cc, fromState);
            } catch (DispatchException e) {
                throw new ActionException(action, e);
            }
        }
        return ConfigurationResult.newInstance(configuration);

    }

    private <T extends ComponentType<T, C, I, S, ?>, C extends Component<C, T, I, S, ?>, I extends ComponentId, S extends ComponentState<S>>
    void changeComponentState(Dispatcher dispatcher, ComponentConfiguration<?, T, C, S> visited, ComponentStateType fromState) throws DispatchException {
        C component = visited.getComponent();
        S oldState = component.getCurrentState();
        S changedState = dispatcher.execute(ChangeComponentState.newInstance(component, visited.getState(fromState))).getValue();
        if (!changedState.equals(oldState)) {
            C changedComponent = component.getType()
                                        .componentBuilder()
                                        .from(component)
                                        .state(changedState)
                                        .validate()
                                        .build();
            visited.setComponent(changedComponent);
        }
    }
}
