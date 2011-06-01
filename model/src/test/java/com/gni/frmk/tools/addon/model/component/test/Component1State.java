package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentState;
import com.google.common.base.Objects;
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
public class Component1State extends BaseComponentState<Component1State> {
    private boolean enabled;

    public Component1State() {
        super();
    }

    public Component1State(Builder builder) {
        super(builder);
        enabled = builder.enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean unknown() {
        return false;
    }

    @Override
    public int compareTo(Component1State other) {
        return ComparisonChain.start()
                              .compare(0, super.compareTo(other))
                              .compare(isEnabled(), other.isEnabled())
                              .result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Component1State that = (Component1State) o;

        return super.equals(that)
               && Objects.equal(enabled, that.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(),enabled);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends BaseComponentState.Builder<Builder, Component1State> {
        private Boolean enabled;

        public Builder() {
            exist(true);
        }

        @Override
        public Component1State build() {
            return new Component1State(this);
        }

        public Builder enable(boolean enabled) {
            this.enabled = enabled;
            return self();
        }

        @Override
        public Builder validate() {
            checkNotNull(enabled);
            return super.validate();
        }

        @Override
        public Builder self() {
            return this;
        }
    }


}
