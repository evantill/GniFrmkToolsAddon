package com.gni.frmk.tools.addon.model.configuration.component;

import com.gni.frmk.tools.addon.model.component.Scheduler;
import com.gni.frmk.tools.addon.model.component.state.SchedulerState;
import com.gni.frmk.tools.addon.service.api.configuration.ComponentConfigurationVisitor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/04/11
 * Time: 11:46
 *
 * @author: e03229
 */
@XmlRootElement
public class SchedulerConfiguration extends ComponentConfiguration<Scheduler, SchedulerState> {

    public SchedulerConfiguration(Builder builder) {
        super(builder);
    }

    @Override
    public void accept(ComponentConfigurationVisitor visitor) {
        visitor.visit(this);
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
            extends ComponentConfiguration.Builder<Builder, SchedulerConfiguration, Scheduler, SchedulerState> {
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
