package com.gni.frmk.tools.addon.model.configuration.component;

import com.gni.frmk.tools.addon.model.component.JmsTrigger;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.service.api.configuration.ComponentConfigurationVisitor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 11:42
 *
 * @author: e03229
 */
@XmlRootElement
public class JmsTriggerConfiguration extends ComponentConfiguration<JmsTrigger, ActivableState> {

    public JmsTriggerConfiguration(Builder builder) {
        super(builder);
    }

    @Override
    public void accept(ComponentConfigurationVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * for jaxb
     */
    private JmsTriggerConfiguration(){
        super();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder
            extends ComponentConfiguration.Builder<Builder, JmsTriggerConfiguration, JmsTrigger, ActivableState> {
        @Override
        protected JmsTriggerConfiguration buildObjectBeforeValidation() {
            return new JmsTriggerConfiguration(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
