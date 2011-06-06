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
    public int compareTo(TemporaryActivableState other) {
        return ComparisonChain.start()
                              .compare(0, super.compareTo(other))
                              .compare(getTemporary(), other.getTemporary())
                              .compare(getActivable(), other.getActivable())
                              .result();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TemporaryActivableState that = (TemporaryActivableState) o;
        return Objects.equal(exist(), that.exist())
               && Objects.equal(activable, that.activable)
               && Objects.equal(temporary, that.temporary);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(exist(), activable, temporary);
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


}
