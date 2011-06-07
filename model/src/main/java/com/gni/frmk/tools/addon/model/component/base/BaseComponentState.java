package com.gni.frmk.tools.addon.model.component.base;

import com.gni.frmk.tools.addon.model.BuilderWithValidation;
import com.gni.frmk.tools.addon.model.component.ComponentState;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 11:24
 *
 * @author: e03229
 */
public abstract class BaseComponentState<T extends BaseComponentState<T>>
        implements ComponentState<T>, Comparable<T> {
    private boolean exist;

    protected BaseComponentState() {
        exist = false;
    }

    protected BaseComponentState(Builder<?, T> builder) {
        builder.validate();
        exist = builder.exist;
    }

    @XmlAttribute
    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    @Override
    public boolean exist() {
        return isExist();
    }

    @Override
    public int compareTo(T other) {
        return ComparisonChain.start().compare(isExist(), other.isExist()).result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseComponentState)) return false;

        BaseComponentState that = (BaseComponentState) o;

        return Objects.equal(exist,that.exist);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(exist());
    }

    @XmlTransient
    protected abstract static class Builder
            <B extends Builder<B, T>,
                    T extends BaseComponentState<T>>
            implements BuilderWithValidation<B, T> {
        private Boolean exist;

        public B exist(boolean exist) {
            this.exist = exist;
            return self();
        }

        @Override
        public B validate() {
            checkNotNull(exist);
            return self();
        }
    }
}
