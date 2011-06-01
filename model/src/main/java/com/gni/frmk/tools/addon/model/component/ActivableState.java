package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentState;

import static com.gni.frmk.tools.addon.model.component.EnableStatus.UNKNOWN;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:10
 *
 * @author: e03229
 */
public class ActivableState extends BaseComponentState<ActivableState> {

    private ActivableStatus activable;
    private EnableStatus enabled;

    private ActivableState() {
        super();
        enabled = EnableStatus.UNKNOWN;
        activable = ActivableStatus.UNKNOWN;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static ActivableState build(EnableStatus enabled, ActivableStatus activable) {
        return builder().enable(enabled).activable(activable).validate().build();
    }

    public ActivableState(Builder builder) {
        super(builder);
        activable = builder.activable;
        enabled = builder.enabled;
    }

    public ActivableStatus getActivable() {
        return activable;
    }

    public void setActivable(ActivableStatus activable) {
        this.activable = activable;
    }

    public EnableStatus getEnabled() {
        return enabled;
    }

    public void setEnabled(EnableStatus enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean unknown() {
        return activable == ActivableStatus.UNKNOWN || enabled == EnableStatus.UNKNOWN;
    }

    public static final class Builder extends BaseComponentState.Builder<Builder, ActivableState> {
        private EnableStatus enabled;
        private ActivableStatus activable;

        public Builder enable(EnableStatus enabled) {
            this.enabled = checkNotNull(enabled);
            return this;
        }

        public Builder activable(ActivableStatus activable) {
            this.activable = checkNotNull(activable);
            return this;
        }

        @Override
        public ActivableState build() {
            boolean unknown = enabled == EnableStatus.UNKNOWN || activable == ActivableStatus.UNKNOWN;
            exist(!unknown);
            return new ActivableState(this);
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Builder validate() {
            checkNotNull(enabled);
            checkNotNull(activable);
            return super.validate();
        }
    }

}
