package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.visitors.ComponentVisitor;

import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 14:18
 *
 * @author: e03229
 */
public class JmsAlias extends PackageAware<StringId, ConnectableState> {

    @NotNull
    private final String name;

    @NotNull
    private final String description;

    public JmsAlias(Builder<?, ?, StringId, ConnectableState> builder) {
        super(builder);
        name = builder.name;
        description = builder.description;
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public static JmsAliasBuilder builder() {
        return new JmsAliasBuilder();
    }

    public static class JmsAliasBuilder extends Builder<JmsAliasBuilder, JmsAlias, StringId, ConnectableState> {

        public JmsAliasBuilder() {
            defineType(ComponentType.JMS_ALIAS);
        }

        @Override
        public JmsAliasBuilder self() {
            return this;
        }

        @Override
        public Builder<JmsAliasBuilder, JmsAlias, StringId, ConnectableState> name(String value) {
            super.name(value);
            defineId(new StringId(name));
            return self();
        }

        @Override
        public JmsAlias build() {
            return new JmsAlias(this);
        }
    }

    public static abstract class Builder<T extends Builder<T, B, I, S>, B extends PackageAware<I, S>, I extends StringId, S extends ConnectableState>
            extends PackageAware.Builder<T, B, I, S> {

        protected String name;
        protected String description;

        public Builder<T, B, I, S> name(String value) {
            name = value;
            return self();
        }

        public Builder<T, B, I, S> description(String value) {
            description = value;
            return self();
        }
    }
}

