package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentId;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 11:32
 *
 * @author: e03229
 */
@XmlRootElement
public class Component2Id
        extends BaseComponentId<Component2Id> {
    private Integer value;

    protected Component2Id() {
    }

    public Component2Id(Builder builder) {
        super(builder);
        value = builder.value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public int compareTo(Component2Id o) {
        return ComparisonChain.start()
                              .compare(value, o.value)
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Component2Id that = (Component2Id) o;
        return Objects.equal(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("value", value).toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Component2Id build(int value) {
        return builder().value(value).build();
    }

    @XmlTransient
    public static final class Builder extends BaseComponentId.Builder<Builder, Component2Id> {
        private Integer value;

        @Override
        public Component2Id build() {
            return new Component2Id(this);
        }

        @Override
        public Builder self() {
            return this;
        }

        public Builder value(Integer value) {
            this.value = checkNotNull(value);
            return self();
        }

        @Override
        public Builder validate() {
            checkNotNull(value);
            return self();
        }
    }

}
