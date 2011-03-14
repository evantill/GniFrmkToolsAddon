package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.components.NativeTrigger.NativeTriggerState;
import com.gni.frmk.tools.addon.configuration.visitors.ComponentVisitor;

import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:18
 *
 * @author: e03229
 */
public class NativeTrigger extends PackageAware<StringId, NativeTriggerState> {

    @NotNull
    private final String name;

    public NativeTrigger(Builder<?, ?, StringId, NativeTriggerState> builder) {
        super(builder);
        name = builder.name;
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public static NativeTriggerBuilder builder() {
        return new NativeTriggerBuilder();
    }

    public static class NativeTriggerBuilder extends Builder<NativeTriggerBuilder, NativeTrigger, StringId, NativeTriggerState> {

        public NativeTriggerBuilder() {
            defineType(ComponentType.NATIVE_TRIGGER);
        }

        @Override
        public NativeTriggerBuilder self() {
            return this;
        }

        @Override
        public Builder<NativeTriggerBuilder, NativeTrigger, StringId, NativeTriggerState> name(String value) {
            super.name(value);
            defineId(new StringId(name));
            return self();
        }

        @Override
        public NativeTrigger build() {
            return new NativeTrigger(this);
        }
    }

    public static abstract class Builder<T extends Builder<T, B, I, S>, B extends PackageAware<I, S>, I extends StringId, S extends NativeTriggerState>
            extends PackageAware.Builder<T, B, I, S> {

        protected String name;

        public Builder<T, B, I, S> name(String value) {
            name = value;
            return self();
        }
    }

    public static class NativeTriggerState implements ComponentState {

        final EnableState enabled;
        final TemporaryActivableState retrievalState;
        final TemporaryActivableState processingState;

        public NativeTriggerState(EnableState enabled, TemporaryActivableState retrievalState, TemporaryActivableState processingState) {
            this.enabled = enabled;
            this.retrievalState = retrievalState;
            this.processingState = processingState;
        }

        public EnableState getEnabled() {
            return enabled;
        }

        public TemporaryActivableState getRetrievalState() {
            return retrievalState;
        }

        public TemporaryActivableState getProcessingState() {
            return processingState;
        }

        @Override
        public ComponentStateStatus getComponentStatus() {
            return enabled.getComponentStatus()
                          .composeWith(retrievalState.getComponentStatus())
                          .composeWith(processingState.getComponentStatus());
        }
    }
}
