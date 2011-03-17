package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.components.ActivableState.ActivableStatus;
import com.gni.frmk.tools.addon.configuration.components.NativeTrigger.NativeTriggerState;
import com.gni.frmk.tools.addon.configuration.components.TemporaryActivableState.TemporaryStatus;
import com.gni.frmk.tools.addon.configuration.visitors.ComponentVisitor;

import javax.validation.constraints.NotNull;

import static com.google.common.base.Preconditions.checkNotNull;

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

    public NativeTrigger(NativeTriggerBuilder builder) {
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

    public static class NativeTriggerBuilder extends PackageAware.Builder<NativeTriggerBuilder, NativeTrigger, StringId, NativeTriggerState> {

        private String name;

        public NativeTriggerBuilder() {
            defineType(ComponentType.NATIVE_TRIGGER);
        }

        @Override
        public NativeTriggerBuilder self() {
            return this;
        }

        public NativeTriggerBuilder name(String value) {
            name = checkNotNull(value);
            defineId(new StringId(name));
            return self();
        }

        @Override
        public NativeTrigger build() {
            return new NativeTrigger(this);
        }
    }

    public static class NativeTriggerState implements ComponentState {

        private final EnableState enabled;
        private final TemporaryActivableState retrievalState;
        private final TemporaryActivableState processingState;

        public NativeTriggerState(Builder builder) {
            this.enabled = builder.enabled;
            this.retrievalState = builder.retrievalState;
            this.processingState = builder.processingState;
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

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {
            private EnableState enabled;
            private TemporaryActivableState retrievalState;
            private TemporaryActivableState processingState;

            public Builder defineEnable(com.gni.frmk.tools.addon.configuration.components.EnableState.EnableStatus status) {
                enabled = new EnableState(status);
                return this;
            }

            public Builder defineRetrieval(TemporaryStatus temporary, ActivableStatus status) {
                retrievalState = new TemporaryActivableState(temporary, status);
                return this;
            }

            public Builder defineRetrieval(TemporaryActivableState state) {
                retrievalState = state;
                return this;
            }

            public Builder defineProcessing(TemporaryStatus temporary, ActivableStatus status) {
                processingState = new TemporaryActivableState(temporary, status);
                return this;
            }

            public Builder defineProcessing(TemporaryActivableState state) {
                processingState = state;
                return this;
            }

            public NativeTriggerState build() {
                return new NativeTriggerState(this);
            }
        }
    }
}
