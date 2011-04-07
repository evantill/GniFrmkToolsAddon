package com.gni.frmk.tools.addon.model.configuration.component;

import com.gni.frmk.tools.addon.model.component.Port;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.service.api.configuration.ComponentConfigurationVisitor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 11:46
 *
 * @author: e03229
 */
@XmlRootElement
public class PortConfiguration extends ComponentConfiguration<Port, ActivableState> {

    public PortConfiguration(Builder builder) {
        super(builder);
    }

    @Override
    public void accept(ComponentConfigurationVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * for jaxb
     */
    private PortConfiguration() {
        super();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder
            extends ComponentConfiguration.Builder<Builder, PortConfiguration, Port, ActivableState> {
        @Override
        protected PortConfiguration buildObjectBeforeValidation() {
            return new PortConfiguration(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
