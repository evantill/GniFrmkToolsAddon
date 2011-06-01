package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentState;
import com.google.common.collect.ComparisonChain;

import static com.gni.frmk.tools.addon.model.component.EnableStatus.UNKNOWN;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 11:55
 *
 * @author: e03229
 */
public final class EnableState extends BaseComponentState<EnableState> {

    private EnableStatus enabled = UNKNOWN;

    protected EnableState() {
        super();
    }

    public EnableState(Builder builder) {
        super(builder);
        enabled = builder.enabled;
    }

    public EnableStatus getEnabled() {
        return enabled;
    }

    public void setEnabled(EnableStatus enabled) {
        this.enabled = enabled;
    }

    @Override
    public int compareTo(EnableState other) {
        return ComparisonChain.start()
                              .compare(0, super.compareTo(other))
                              .compare(getEnabled(), other.getEnabled())
                              .result();
    }

    @Override
    public boolean unknown() {
        return enabled == UNKNOWN;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static EnableState build(EnableStatus enabled) {
        return builder().enable(enabled).validate().build();
    }

    public static final class Builder extends BaseComponentState.Builder<Builder, EnableState> {
        private EnableStatus enabled;

        public Builder enable(EnableStatus enabled) {
            this.enabled = checkNotNull(enabled);
            switch (enabled) {
                case UNKNOWN:
                    exist(false);
                    break;
                default:
                    exist(true);
                    break;
            }
            return this;
        }

        @Override
        public EnableState build() {
            return new EnableState(this);
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Builder validate() {
            checkNotNull(enabled);
            return super.validate();
        }
    }

}
