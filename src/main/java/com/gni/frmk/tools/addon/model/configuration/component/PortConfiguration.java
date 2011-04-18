package com.gni.frmk.tools.addon.model.configuration.component;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.component.Port;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;

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
    protected void doAcceptSimple(ConfigurationVisitor visitor) {
        visitor.visitComponentConfiguration(this);
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
