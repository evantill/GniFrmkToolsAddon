package com.gni.frmk.tools.addon.model.configuration.component;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.component.ImmutableAdapterNotification;
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
public class AdapterNotificationConfiguration extends ComponentConfiguration<ImmutableAdapterNotification, ActivableState> {

    public AdapterNotificationConfiguration(Builder builder) {
        super(builder);
    }

    @Override
    protected void doAcceptSimple(ConfigurationVisitor visitor) {
        visitor.visitComponentConfiguration(this);
    }
    /**
     * for jaxb
     */
    private AdapterNotificationConfiguration() {
        super();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder
            extends ComponentConfiguration.Builder<Builder, AdapterNotificationConfiguration, ImmutableAdapterNotification, ActivableState> {
        @Override
        protected AdapterNotificationConfiguration buildObjectBeforeValidation() {
            return new AdapterNotificationConfiguration(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
