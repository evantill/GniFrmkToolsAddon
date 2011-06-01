package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentId;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlRootElement;

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
                .compare(value,o.value)
                .result();
    }

    public static Builder builder() {
        return new Builder();
    }

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
