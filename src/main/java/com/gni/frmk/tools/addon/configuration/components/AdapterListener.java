package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.visitors.ComponentVisitor;

import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:08
 *
 * @author: e03229
 */
public class AdapterListener extends AdapterTypeAware<StringId, ActivableState> {

    @NotNull
    private final String name;

    public AdapterListener(Builder<?, ?, StringId, ActivableState> builder) {
        super(builder);
        name = builder.name;
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public static AdapterListenerBuilder builder() {
        return new AdapterListenerBuilder();
    }

    public static class AdapterListenerBuilder extends Builder<AdapterListenerBuilder, AdapterListener, StringId, ActivableState> {

        public AdapterListenerBuilder() {
            defineType(ComponentType.ADAPTER_LISTENER);
        }

        @Override
        public AdapterListenerBuilder self() {
            return this;
        }

        @Override
        public Builder<AdapterListenerBuilder, AdapterListener, StringId, ActivableState> name(String value) {
            super.name(value);
            defineId(new StringId(name));
            return self();
        }

        @Override
        public AdapterListener build() {
            return new AdapterListener(this);
        }
    }

    public static abstract class Builder<T extends Builder<T, B, I, S>, B extends AdapterTypeAware<I, S>, I extends StringId, S extends ActivableState>
            extends AdapterTypeAware.Builder<T, B, I, S> {

        protected String name;

        public Builder<T, B, I, S> name(String value) {
            name = value;
            return self();
        }
    }
}
