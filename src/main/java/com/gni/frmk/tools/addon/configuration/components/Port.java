package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.visitors.ComponentVisitor;

import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:18
 *
 * @author: e03229
 */
public class Port extends PackageAware<StringId, ActivableState> {

    @NotNull
    private final String key;

    public Port(Builder<?, ?, StringId, ActivableState> builder) {
        super(builder);
        key = builder.key;
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public String getKey() {
        return key;
    }

    public static PortBuilder builder() {
        return new PortBuilder();
    }

    public static class PortBuilder extends Builder<PortBuilder, Port, StringId, ActivableState> {

        public PortBuilder() {
            defineType(ComponentType.PORT);
        }

        @Override
        public PortBuilder self() {
            return this;
        }

        @Override
        public Builder<PortBuilder, Port, StringId, ActivableState> key(String value) {
            super.key(value);
            defineId(new StringId(key));
            return self();
        }

        @Override
        public Port build() {
            return new Port(this);
        }
    }

    public static abstract class Builder<T extends Builder<T, B, I, S>, B extends PackageAware<I, S>, I extends StringId, S extends ActivableState>
            extends PackageAware.Builder<T, B, I, S> {

        protected String key;

        public Builder<T, B, I, S> key(String value) {
            key = value;
            return self();
        }
    }
}
