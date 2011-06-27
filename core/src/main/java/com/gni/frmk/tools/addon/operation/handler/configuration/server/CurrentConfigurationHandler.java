package com.gni.frmk.tools.addon.operation.handler.configuration.server;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentDetail;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentType;
import com.gni.frmk.tools.addon.model.configuration.Configuration;
import com.gni.frmk.tools.addon.model.configuration.base.BaseComponentConfiguration;
import com.gni.frmk.tools.addon.model.configuration.base.BaseConfiguration;
import com.gni.frmk.tools.addon.model.configuration.base.BaseConfiguration.Builder;
import com.gni.frmk.tools.addon.model.configuration.base.BaseConfigurationId;
import com.gni.frmk.tools.addon.model.configuration.base.BaseConfigurationInformation;
import com.gni.frmk.tools.addon.operation.action.component.GetComponentsByType;
import com.gni.frmk.tools.addon.operation.action.component.ListComponentTypes;
import com.gni.frmk.tools.addon.operation.action.configuration.server.CurrentConfiguration;
import com.gni.frmk.tools.addon.operation.api.ActionException;
import com.gni.frmk.tools.addon.operation.api.ActionHandler;
import com.gni.frmk.tools.addon.operation.api.DispatchException;
import com.gni.frmk.tools.addon.operation.api.Dispatcher;
import com.gni.frmk.tools.addon.operation.context.InvokeContext;
import com.gni.frmk.tools.addon.operation.result.ListResult;
import com.gni.frmk.tools.addon.operation.result.SetResult;
import com.gni.frmk.tools.addon.operation.result.SingleResult;

import javax.enterprise.util.TypeLiteral;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/05/11
 * Time: 11:32
 *
 * @author: e03229
 */
public class CurrentConfigurationHandler implements ActionHandler<CurrentConfiguration, SingleResult<Configuration>, InvokeContext> {

    private static final TypeLiteral<CurrentConfiguration> TYPE_LITERAL = new TypeLiteral<CurrentConfiguration>() {
    };
    private static final String CONFIGURATION_DEFAULT_NAME = "default";
    private static final String CONFIGURATION_DEFAULT_PACKAGE = "Default";

    @Override
    public TypeLiteral<CurrentConfiguration> getActionType() {
        return TYPE_LITERAL;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public SingleResult<Configuration> execute(CurrentConfiguration action, InvokeContext context) throws ActionException {
        Dispatcher dispatcher = context.getDispatcher();
        BaseConfigurationId configurationId = BaseConfigurationId.builder()
                                                                 .id(CONFIGURATION_DEFAULT_NAME)
                                                                 .packageName(CONFIGURATION_DEFAULT_PACKAGE)
                                                                 .build();
        BaseConfigurationInformation configurationInfo = BaseConfigurationInformation.builder()
                                                                                     .name(CONFIGURATION_DEFAULT_NAME)
                                                                                     .creationAndModification(action.getNow())
                                                                                     .build();
        Builder builder = BaseConfiguration.builder().id(configurationId).info(configurationInfo);
        try {
            SetResult<ComponentType<?, ?, ?, ?, ?>> types = dispatcher.execute(new ListComponentTypes());
            for (ComponentType type : types.getCollection()) {
                addComponents(dispatcher, builder, type);
            }
            return SingleResult.<Configuration>newInstance(builder.validate().build());
        } catch (DispatchException cause) {
            throw new ActionException(action, cause);
        }
    }

    private <T extends ComponentType<T, C, I, S, D>,
            C extends Component<C, T, I, S, D>,
            I extends ComponentId<I>,
            S extends ComponentState<S>,
            D extends ComponentDetail<D>>
    Builder addComponents(Dispatcher dispatcher, Builder builder, T type) throws DispatchException {
        ListResult<C> components = dispatcher.execute(new GetComponentsByType<T, C, I, S, D>(type));
        for (C component : components.getCollection()) {
            builder.addComponentConfiguration(BaseComponentConfiguration.builder(type)
                                                                        .presentOnIS(true)
                                                                        .component(component)
                                                                        .build());
        }
        return builder;
    }
}
