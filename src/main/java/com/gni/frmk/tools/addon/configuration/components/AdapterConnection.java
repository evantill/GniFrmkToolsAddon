package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.visitors.ComponentVisitor;

import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 09/03/11
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
public class AdapterConnection extends AdapterTypeAware<StringId, EnableState> {

    @NotNull
    private final String alias;

    public AdapterConnection(Builder<?, ?, StringId, EnableState> builder) {
        super(builder);
        alias = builder.alias;
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public String getAlias() {
        return alias;
    }

    public static AdapterConnectionBuilder builder() {
        return new AdapterConnectionBuilder();
    }

    public static class AdapterConnectionBuilder extends Builder<AdapterConnectionBuilder, AdapterConnection, StringId, EnableState> {

        public AdapterConnectionBuilder() {
            defineType(ComponentType.ADAPTER_CONNECTION);
        }

        @Override
        public AdapterConnectionBuilder self() {
            return this;
        }

        @Override
        public Builder<AdapterConnectionBuilder, AdapterConnection, StringId, EnableState> alias(String value) {
            super.alias(value);
            defineId(new StringId(alias));
            return self();
        }

        @Override
        public AdapterConnection build() {
            return new AdapterConnection(this);
        }
    }

    public static abstract class Builder<T extends Builder<T, B, I, S>, B extends AdapterTypeAware<I, S>, I extends StringId, S extends EnableState>
            extends AdapterTypeAware.Builder<T, B, I, S> {

        protected String alias;

        public Builder<T, B, I, S> alias(String value) {
            alias = value;
            return self();
        }
    }

}
