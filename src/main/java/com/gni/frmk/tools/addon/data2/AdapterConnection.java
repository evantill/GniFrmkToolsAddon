package com.gni.frmk.tools.addon.data2;


import com.gni.frmk.tools.addon.data2.AdapterTypeComponent.Builder;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/02/11
 * Time: 09:48
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class AdapterConnection extends AdapterTypeComponent<EnableComponentState> {

    public static final ComponentType TYPE = ComponentType.ADAPTER_CONNECTION;
    protected static final String ALIAS_KEY = "alias";

    private AdapterConnection(Builder<? extends EnableComponentState,?> builder) {
        super(builder);
    }

    /**
     * empty constructor for jaxb.
     */
    @SuppressWarnings({"UnusedDeclaration"})
    private AdapterConnection() {
        super();
    }

    @XmlTransient
    public String getAlias() {
        return findRequiredDetail(ALIAS_KEY).getValue();
    }

    public void setAlias(String alias) {
        addDetail(new ComponentDetail(ALIAS_KEY, alias));
    }

    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public static AdapterConnectionBuilder builder() {
        return new AdapterConnectionBuilder();
    }

    public static abstract class Builder<S extends EnableComponentState,T extends Builder<S,T>>
            extends AdapterTypeComponent.Builder<EnableComponentState, T> {

        private String aliasName;


        public Builder() {
            type(TYPE);
        }

        public T alias(String value) {
            aliasName = checkNotNull(value, "alias required");
            id(value);
            addDetail(ALIAS_KEY, aliasName);
            return self();
        }

        @Override
        public T check() throws CheckException {
            super.check();
            try {
                checkNotNull(aliasName, "alias required");
            } catch (NullPointerException npex) {
                throw new CheckException(npex);
            }
            return self();
        }

        @Override
        public AdapterConnection build() {
            return new AdapterConnection(this);
        }
    }

    public static class AdapterConnectionBuilder extends Builder<EnableComponentState,AdapterConnectionBuilder> {
        @Override
        protected AdapterConnectionBuilder self() {
            return this;
        }
    }
}