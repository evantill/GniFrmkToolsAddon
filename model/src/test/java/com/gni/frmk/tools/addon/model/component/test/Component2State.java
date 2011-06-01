package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentState;
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
public class Component2State extends BaseComponentState<Component2State> {
    private boolean active;
    private boolean enabled;

    public Component2State() {
        super();
    }

    public Component2State(Builder builder) {
        super(builder);
        active = builder.active;
        enabled = builder.enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean unknown() {
        return false;
    }

    @Override
    public int compareTo(Component2State other) {
        return ComparisonChain.start()
                              .compare(0, super.compareTo(other))
                              .compare(isEnabled(), other.isEnabled())
                              .result();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends BaseComponentState.Builder<Builder, Component2State> {
        private Boolean enabled;
        public Boolean active;

        public Builder() {
            exist(true);
        }

        @Override
        public Component2State build() {
            return new Component2State(this);
        }

        public Builder enable(boolean enabled) {
            this.enabled = enabled;
            return self();
        }

        public Builder active(boolean active) {
            this.active = active;
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
