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

    public Port(PortBuilder builder) {
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

    public static class PortBuilder extends PackageAware.Builder<PortBuilder, Port, StringId, ActivableState> {

        protected String key;

        public PortBuilder() {
            defineType(ComponentType.PORT);
        }

        @Override
        public PortBuilder self() {
            return this;
        }

        public PortBuilder key(String value) {
            key = value;
            defineId(new StringId(key));
            return self();
        }

        @Override
        public Port build() {
            return new Port(this);
        }

    }
}
