package com.gni.frmk.tools.addon.model.configuration.component;

import com.gni.frmk.tools.addon.model.component.AdapterListener;
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
public class AdapterListenerConfiguration extends ComponentConfiguration<AdapterListener, ActivableState> {

    public AdapterListenerConfiguration(Builder builder) {
        super(builder);
    }

    @Override
    public void accept(ComponentConfigurationVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * for jaxb
     */
    private AdapterListenerConfiguration() {
        super();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder
            extends ComponentConfiguration.Builder<Builder, AdapterListenerConfiguration, AdapterListener, ActivableState> {
        @Override
        protected AdapterListenerConfiguration buildObjectBeforeValidation() {
            return new AdapterListenerConfiguration(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
