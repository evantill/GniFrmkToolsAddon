package com.gni.frmk.tools.addon.data2;


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
public class AdapterConnection extends AdapterTypeComponent {

    public static final ComponentType TYPE = ComponentType.ADAPTER_CONNECTION;
    protected static final String ALIAS_KEY = "alias";

    private AdapterConnection(Builder<?> builder) {
        super(builder);
    }

    /**
     * empty constructor for jaxb.
     */
    @SuppressWarnings({"UnusedDeclaration"})
    private AdapterConnection() {
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

    public static Builder<AdapterConnectionBuilder> builder() {
        return new AdapterConnectionBuilder();
    }

    public static abstract class Builder<T extends Builder<T>> extends AdapterTypeComponent.Builder<T> {

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

    public static class AdapterConnectionBuilder extends Builder<AdapterConnectionBuilder> {
        @Override
        protected AdapterConnectionBuilder self() {
            return this;
        }
    }
}
