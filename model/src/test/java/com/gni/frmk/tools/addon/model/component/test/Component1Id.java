package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentId;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlElement;
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
public class Component1Id extends BaseComponentId<Component1Id> {
    private String value;

    protected Component1Id() {
    }

    public Component1Id(Builder builder) {
        super(builder);
        value = builder.value;
    }

    @XmlElement
    public String getValue() {
        return value;
    }

    private void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(Component1Id o) {
        return ComparisonChain.start()
                              .compare(value, o.value)
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Component1Id that = (Component1Id) o;
        return Objects.equal(value,that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    public static Builder builder() {
        return new Builder();
    }

    @XmlTransient
    public static final class Builder extends BaseComponentId.Builder<Builder, Component1Id> {
        private String value;

        @Override
        public Component1Id build() {
            return new Component1Id(this);
        }

        @Override
        public Builder self() {
            return this;
        }

        public Builder value(String value) {
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
