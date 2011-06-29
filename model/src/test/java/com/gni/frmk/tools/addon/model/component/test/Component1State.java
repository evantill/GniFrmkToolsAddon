package com.gni.frmk.tools.addon.model.component.test;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentState;
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
public class Component1State extends BaseComponentState<Component1State> {
    public static final Component1State OPENED = newInstance(true);
    public static final Component1State CLOSED = newInstance(false);
    public static final Component1State UNKNOWN = newInstance(false);

    private boolean enabled;

    public Component1State() {
        super();
    }

    public Component1State(Builder builder) {
        super(builder);
        enabled = builder.enabled;
    }

    @XmlElement
    public boolean isEnabled() {
        return enabled;
    }

    private void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean unknown() {
        return false;
    }

    @Override
    public Component1State getOpenState() {
        return OPENED;
    }

    @Override
    public Component1State getCloseState() {
        return CLOSED;
    }

    @Override
    protected ComparisonChain extendedCompareTo(ComparisonChain chain, Component1State other) {
        return chain.compare(isEnabled(), other.isEnabled());
    }

    @Override
    protected boolean extendedEquals(Component1State other) {
        return Objects.equal(enabled, other.enabled);
    }

    @Override
    protected Object[] extendedHashCode() {
        return new Object[]{enabled};
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Component1State newInstance(boolean enabled) {
        return builder().enable(enabled).validate().build();
    }

    @XmlTransient
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
