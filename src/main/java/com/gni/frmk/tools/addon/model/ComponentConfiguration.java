package com.gni.frmk.tools.addon.model;

import com.gni.frmk.tools.addon.visitor.ConfigurationVisitor;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:02
 *
 * @author: e03229
 */
public interface ComponentConfiguration<C extends Component<?, S, ?>, S extends Component.State> {
    C getComponent();

    Map<Component.StateType, S> getStateConfigurations();

    boolean isPresentOnIS();

    void accept(ConfigurationVisitor visitor);
}

