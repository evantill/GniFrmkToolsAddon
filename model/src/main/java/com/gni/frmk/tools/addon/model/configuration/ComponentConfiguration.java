package com.gni.frmk.tools.addon.model.configuration;

import com.gni.frmk.tools.addon.model.component.Component;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.gni.frmk.tools.addon.model.component.ComponentStateType;
import com.gni.frmk.tools.addon.visitor.api.ConfigurationVisitor;
import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorNode;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/05/11
 * Time: 17:02
 *
 * @author: e03229
 */
public interface ComponentConfiguration<C extends Component<C,?,?, S, ?>, S extends ComponentState> {
    C getComponent();

    Map<ComponentStateType, S> getStateConfigurations();

    boolean isPresentOnIS();

    void accept(ConfigurationVisitor visitor);
}

