package com.gni.frmk.tools.addon.model.component.root;

import com.gni.frmk.tools.addon.model.component.ActivableStatus;
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
 * Date: 17/05/11
 * Time: 18:06
 *
 * @author: e03229
 */
@XmlRootElement
@XmlType(propOrder = {
        "activable",
        "temporary"
})
public class TemporaryActivableState
        extends BaseComponentState<TemporaryActivableState> {

    public static final TemporaryActivableState OPENED = build(TemporaryStatus.PERMANENT, ActivableStatus.ACTIVE);
    public static final TemporaryActivableState CLOSED = build(TemporaryStatus.PERMANENT, ActivableStatus.INACTIVE);

    private ActivableStatus activable;
    private TemporaryStatus temporary;

    public TemporaryActivableState(Builder builder) {
        super(builder);
        this.temporary = builder.temporary;
        this.activable = builder.activable;
    }

    private TemporaryActivableState() {
    }

    @XmlElement
    public TemporaryStatus getTemporary() {
        return temporary;
    }

    private void setTemporary(TemporaryStatus temporary) {
        this.temporary = temporary;
    }

    @XmlElement
    public ActivableStatus getActivable() {
        return activable;
    }

    private void setActivable(ActivableStatus activable) {
        this.activable = activable;
    }

    @Override
    public boolean unknown() {
        return temporary == TemporaryStatus.UNKNOWN || activable == ActivableStatus.UNKNOWN;
    }

    @Override
    public TemporaryActivableState getOpenState() {
        return OPENED;
    }

    @Override
    public TemporaryActivableState getCloseState() {
        return CLOSED;
    }

    @Override
    protected ComparisonChain extendedCompareTo(ComparisonChain chain, TemporaryActivableState other) {
        return chain.compare(getTemporary(), other.getTemporary())
                    .compare(getActivable(), other.getActivable());
    }

    @Override
    protected boolean extendedEquals(TemporaryActivableState other) {
        return Objects.equal(activable, other.activable)
               && Objects.equal(temporary, other.temporary);
    }

    @Override
    protected Object[] extendedHashCode() {
        return new Object[]{activable,
                            temporary};
    }

    public static Builder builder() {
        return new Builder();
    }

    @XmlTransient
    public static final class Builder
            extends BaseComponentState.Builder<Builder, TemporaryActivableState> {
        private TemporaryStatus temporary;
        private ActivableStatus activable;

        public Builder temporary(TemporaryStatus value) {
            temporary = checkNotNull(value);
            updateExist();
            return this;
        }

        public Builder activable(ActivableStatus value) {
            activable = checkNotNull(value);
            updateExist();
            return this;
        }

        @Override
        public TemporaryActivableState build() {
            return new TemporaryActivableState(this);
        }

        private void updateExist() {
            boolean unknown = activable == ActivableStatus.UNKNOWN || temporary == TemporaryStatus.UNKNOWN;
            exist(!unknown);
        }

        @Override
        public Builder self() {
            return this;
        }
    }


    public static final TemporaryActivableState build(TemporaryStatus temporary, ActivableStatus activable) {
        return builder().temporary(temporary).activable(activable).validate().build();
    }
}
