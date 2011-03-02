package com.gni.frmk.tools.addon.data2;

import javax.xml.bind.annotation.XmlTransient;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 28/02/11
 * Time: 18:31
 * To change this template use File | Settings | File Templates.
 */
public abstract class AdapterTypeComponent extends PackageComponent {

    private static final String ADAPTER_TYPE_KEY = "adapterType";

    public AdapterTypeComponent(Builder<?> builder) {
        super(builder);
    }

    /**
     * empty constructor for jaxb.
     */
    protected AdapterTypeComponent() {
    }

    @XmlTransient
    public String getAdapterType() {
        return findRequiredDetail(ADAPTER_TYPE_KEY).getValue();
    }

    public void setAdapterType(String value) {
        addDetail(new ComponentDetail(ADAPTER_TYPE_KEY, value));
    }

    public static abstract class Builder<T extends Builder<T>> extends PackageComponent.Builder<T> {

        private String adapterType;

        public T adapterType(String value) {
            adapterType = checkNotNull(value, "adapterType required");
            addDetail(ADAPTER_TYPE_KEY, checkNotNull(adapterType, "adapterType required"));
            return self();
        }

        @Override
        public T check() throws CheckException {
            super.check();
            try {
                checkNotNull(adapterType, "adapterType required");
                return self();
            } catch (NullPointerException npex) {
                throw new CheckException(npex);
            }
        }

    }

}
