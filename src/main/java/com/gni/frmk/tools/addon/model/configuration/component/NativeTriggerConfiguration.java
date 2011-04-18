package com.gni.frmk.tools.addon.model.configuration.component;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.component.NativeTrigger;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 11:42
 *
 * @author: e03229
 */
@XmlRootElement
public class NativeTriggerConfiguration extends ComponentConfiguration<NativeTrigger, NativeTriggerState> {

    public NativeTriggerConfiguration(Builder builder) {
        super(builder);
    }

   @Override
    protected void doAcceptSimple(ConfigurationVisitor visitor) {
        visitor.visitComponentConfiguration(this);
    }

    /**
     * for jaxb
     */
    private NativeTriggerConfiguration() {
        super();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder
            extends ComponentConfiguration.Builder<Builder, NativeTriggerConfiguration, NativeTrigger, NativeTriggerState> {
        @Override
        protected NativeTriggerConfiguration buildObjectBeforeValidation() {
            return new NativeTriggerConfiguration(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
