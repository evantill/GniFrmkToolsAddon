package com.gni.frmk.tools.addon.model.configuration.component;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.component.JmsAlias;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 11:42
 *
 * @author: e03229
 */
@XmlRootElement
public class JmsAliasConfiguration extends ComponentConfiguration<JmsAlias, ConnectableState> {

    public JmsAliasConfiguration(Builder builder) {
        super(builder);
    }

    @Override
    protected void doAcceptSimple(ConfigurationVisitor visitor) {
        visitor.visitComponentConfiguration(this);
    }

    /**
     * for jaxb
     */
    private JmsAliasConfiguration() {
        super();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder
            extends ComponentConfiguration.Builder<Builder, JmsAliasConfiguration, JmsAlias, ConnectableState> {
        @Override
        protected JmsAliasConfiguration buildObjectBeforeValidation() {
            return new JmsAliasConfiguration(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
