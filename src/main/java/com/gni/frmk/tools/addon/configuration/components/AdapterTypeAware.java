package com.gni.frmk.tools.addon.configuration.components;

import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 11:52
 *
 * @author: e03229
 */
public abstract class AdapterTypeAware<I extends ComponentId, S extends ComponentState> extends PackageAware<I, S> {

    @NotNull
    private final String adapterType;

    protected AdapterTypeAware(Builder<?, ?, I, S> builder) {
        super(builder);
        adapterType = builder.adapterType;
    }

    public String getAdapterType() {
        return adapterType;
    }

    public static abstract class Builder<T extends Builder<T, B, I, S>, B extends AdapterTypeAware<I, S>, I extends ComponentId, S extends ComponentState>
            extends PackageAware.Builder<T, B, I, S> {

        protected String adapterType;

        public Builder<T, B, I, S> adapterType(String value) {
            adapterType = value;
            return self();
        }

        @Override
        public T from(B source) {
            super.from(source);
            adapterType=source.getAdapterType();
            return self();
        }
    }
}
