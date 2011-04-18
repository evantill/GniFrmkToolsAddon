package com.gni.frmk.tools.addon.model.configuration.component;

import com.gni.frmk.tools.addon.api.visitor.ConfigurationVisitor;
import com.gni.frmk.tools.addon.model.component.ImmutableScheduler;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 11:46
 *
 * @author: e03229
 */
@XmlRootElement
public class SchedulerConfiguration extends ComponentConfiguration<ImmutableScheduler, SchedulerState> {

    public SchedulerConfiguration(Builder builder) {
        super(builder);
    }

   @Override
    protected void doAcceptSimple(ConfigurationVisitor visitor) {
        visitor.visitComponentConfiguration(this);
    }

    /**
     * for jaxb
     */
    private SchedulerConfiguration() {
        super();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder
            extends ComponentConfiguration.Builder<Builder, SchedulerConfiguration, ImmutableScheduler, SchedulerState> {
        @Override
        protected SchedulerConfiguration buildObjectBeforeValidation() {
            return new SchedulerConfiguration(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
