package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.api.visitor.configuration.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentId;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentStateType;
import com.gni.frmk.tools.addon.model.component.ComponentType;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:02
 *
 * @author: e03229
 */
public interface ComponentConfiguration
        <CC extends ComponentConfiguration<CC, T, C,I, S>,
                T extends ComponentType<T, C, I, S, ?>,
                C extends Component<C, T, I, S, ?>,
                I extends ComponentId<I>,
                S extends ComponentState<S>>
        extends Comparable<CC> {

    T getComponentType();

    C getComponent();

    Map<ComponentStateType, S> getStateConfigurations();

    boolean isPresentOnIS();

    void accept(ConfigurationVisitor visitor);

    void setComponent(C component);

    void updateLastState();

    S getState(ComponentStateType type);
}

