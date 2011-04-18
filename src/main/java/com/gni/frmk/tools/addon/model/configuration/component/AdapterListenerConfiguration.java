package com.gni.frmk.tools.addon.model.configuration.component;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.component.AdapterListener;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;

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
    protected void doAcceptSimple(ConfigurationVisitor visitor) {
        visitor.visitComponentConfiguration(this);
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
