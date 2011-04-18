package com.gni.frmk.tools.addon.model.configuration.component;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.component.AdapterConnection;
import com.gni.frmk.tools.addon.model.component.state.EnableState;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 11:42
 *
 * @author: e03229
 */
@XmlRootElement
public class AdapterConnectionConfiguration extends ComponentConfiguration<AdapterConnection, EnableState> {

    public AdapterConnectionConfiguration(Builder builder) {
        super(builder);
    }

    @Override
    protected void doAcceptSimple(ConfigurationVisitor visitor) {
        visitor.visitComponentConfiguration(this);
    }

    /**
     * for jaxb
     */
    private AdapterConnectionConfiguration() {
        super();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder
            extends ComponentConfiguration.Builder<Builder, AdapterConnectionConfiguration, AdapterConnection, EnableState> {
        @Override
        protected AdapterConnectionConfiguration buildObjectBeforeValidation() {
            return new AdapterConnectionConfiguration(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
