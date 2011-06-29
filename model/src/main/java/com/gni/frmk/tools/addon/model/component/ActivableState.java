package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentState;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:10
 *
 * @author: e03229
 */
@XmlRootElement
@XmlType(propOrder = {
        "enabled",
        "activable"
})
public class ActivableState extends BaseComponentState<ActivableState> {

    public static final ActivableState OPENED = build(EnableStatus.ENABLED, ActivableStatus.ACTIVE);
    public static final ActivableState CLOSED = build(EnableStatus.DISABLED, ActivableStatus.INACTIVE);
    public static final ActivableState UNKNOWN = build(EnableStatus.UNKNOWN, ActivableStatus.UNKNOWN);

    private EnableStatus enabled;
    private ActivableStatus activable;

    private ActivableState() {
        super();
        enabled = EnableStatus.UNKNOWN;
        activable = ActivableStatus.UNKNOWN;
    }

    @Override
    protected ComparisonChain extendedCompareTo(ComparisonChain chain, ActivableState other) {
        return chain.compare(enabled, other.enabled)
                    .compare(activable, other.activable);
    }

    @Override
    protected boolean extendedEquals(ActivableState other) {
        return Objects.equal(enabled, other.enabled)
               && Objects.equal(activable, other.activable);
    }

    @Override
    protected Object[] extendedHashCode() {
        return new Object[]{enabled,
                            activable};
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

    @XmlElement
    public ActivableStatus getActivable() {
        return activable;
    }

    private void setActivable(ActivableStatus activable) {
        this.activable = activable;
    }

    @XmlElement
    public EnableStatus getEnabled() {
        return enabled;
    }

    private void setEnabled(EnableStatus enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean unknown() {
        return activable == ActivableStatus.UNKNOWN || enabled == EnableStatus.UNKNOWN;
    }

    @Override
    public ActivableState getOpenState() {
        return OPENED;
    }

    @Override
    public ActivableState getCloseState() {
        return CLOSED;
    }

    @XmlTransient
    public static final class Builder extends BaseComponentState.Builder<Builder, ActivableState> {
        private EnableStatus enabled;
        private ActivableStatus activable;

        public Builder enable(EnableStatus enabled) {
            this.enabled = checkNotNull(enabled);
            updateExist();
            return this;
        }

        public Builder activable(ActivableStatus activable) {
            this.activable = checkNotNull(activable);
            updateExist();
            return this;
        }

        private void updateExist() {
            boolean unknown = activable == ActivableStatus.UNKNOWN || enabled == EnableStatus.UNKNOWN;
            exist(!unknown);
        }

        @Override
        public ActivableState build() {
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
