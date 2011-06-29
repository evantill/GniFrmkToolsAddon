package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.base.BaseComponentState;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import static com.gni.frmk.tools.addon.model.component.EnableStatus.DISABLED;
import static com.gni.frmk.tools.addon.model.component.EnableStatus.ENABLED;
import static com.gni.frmk.tools.addon.model.component.EnableStatus.UNKNOWN;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 11:55
 *
 * @author: e03229
 */
@XmlRootElement
@XmlType(propOrder = {
        "enabled"
})
public final class EnableState extends BaseComponentState<EnableState> {

    public  static final EnableState OPENED =  build(ENABLED);
    public  static final EnableState UNKNOWN =  build(EnableStatus.UNKNOWN);
    public  static final EnableState CLOSED = build(DISABLED);

    private EnableStatus enabled = EnableStatus.UNKNOWN;

    protected EnableState() {
        super();
    }

    public EnableState(Builder builder) {
        super(builder);
        enabled = builder.enabled;
    }

    @XmlElement
    public EnableStatus getEnabled() {
        return enabled;
    }

    private void setEnabled(EnableStatus enabled) {
        this.enabled = enabled;
    }

    @Override
    protected ComparisonChain extendedCompareTo(ComparisonChain chain, EnableState other) {
        return chain.compare(getEnabled(), other.getEnabled());
    }

    @Override
    protected boolean extendedEquals(EnableState other) {
        return Objects.equal(enabled, other.enabled);
    }

    @Override
    protected Object[] extendedHashCode() {
        return new Object[]{enabled};
    }

    @Override
    public boolean unknown() {
        return enabled == EnableStatus.UNKNOWN;
    }

    @Override
    public EnableState getOpenState() {
        return  OPENED;
    }

    @Override
    public EnableState getCloseState() {
        return CLOSED;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static EnableState build(EnableStatus enabled) {
        return builder().enable(enabled).validate().build();
    }

    @XmlTransient
    public static final class Builder extends BaseComponentState.Builder<Builder, EnableState> {
        private EnableStatus enabled;

        public Builder enable(EnableStatus enabled) {
            this.enabled = checkNotNull(enabled);
            //if using switch compiler generate a $1 class and this is a problem for MoxY
            if (enabled == EnableStatus.UNKNOWN) {
                exist(false);
            } else {
                exist(true);
            }
            return this;
        }

        @Override
        public EnableState build() {
            return new EnableState(this);
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Builder validate() {
            checkNotNull(enabled);
            return super.validate();
        }
    }

}
