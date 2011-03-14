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

    public JmsAlias(JmsAliasBuilder builder) {
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

    public String getDescription() {
        return description;
    }

    public static JmsAliasBuilder builder() {
        return new JmsAliasBuilder();
    }

    public static class JmsAliasBuilder extends PackageAware.Builder<JmsAliasBuilder, JmsAlias, StringId, ConnectableState> {

        private String name;
        private String description;

        public JmsAliasBuilder() {
            defineType(ComponentType.JMS_ALIAS);
        }

        @Override
        public JmsAliasBuilder self() {
            return this;
        }

        public JmsAliasBuilder name(String value) {
            name = value;
            defineId(new StringId(name));
            return self();
        }

        public JmsAliasBuilder description(String value) {
            description = value;
            return self();
        }

        @Override
        public JmsAlias build() {
            return new JmsAlias(this);
        }
    }
}

