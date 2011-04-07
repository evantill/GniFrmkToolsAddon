package com.gni.frmk.tools.addon.model.configuration.component;

import com.gni.frmk.tools.addon.model.component.IntegrationServerPackage;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
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
public class IntegrationServerPackageConfiguration extends ComponentConfiguration<IntegrationServerPackage, EnableState> {

    public IntegrationServerPackageConfiguration(Builder builder) {
        super(builder);
    }

    @Override
    public void accept(ComponentConfigurationVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * for jaxb
     */
    private IntegrationServerPackageConfiguration() {
        super();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder
            extends ComponentConfiguration.Builder<Builder, IntegrationServerPackageConfiguration, IntegrationServerPackage, EnableState> {
        @Override
        protected IntegrationServerPackageConfiguration buildObjectBeforeValidation() {
            return new IntegrationServerPackageConfiguration(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
