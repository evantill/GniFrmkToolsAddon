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
public class JmsTrigger extends PackageAware<StringId, ActivableState> {

    @NotNull
    private final String name;

    public JmsTrigger(Builder<?, ?, StringId, ActivableState> builder) {
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

    public static JmsTriggerBuilder builder() {
        return new JmsTriggerBuilder();
    }

    public static class JmsTriggerBuilder extends Builder<JmsTriggerBuilder, JmsTrigger, StringId, ActivableState> {

        public JmsTriggerBuilder() {
            defineType(ComponentType.JMS_TRIGGER);
        }

        @Override
        public JmsTriggerBuilder self() {
            return this;
        }

        @Override
        public Builder<JmsTriggerBuilder, JmsTrigger, StringId, ActivableState> name(String value) {
            super.name(value);
            defineId(new StringId(name));
            return self();
        }

        @Override
        public JmsTrigger build() {
            return new JmsTrigger(this);
        }
    }

    public static abstract class Builder<T extends Builder<T, B, I, S>, B extends PackageAware<I, S>, I extends StringId, S extends ActivableState>
            extends PackageAware.Builder<T, B, I, S> {

        protected String name;

        public Builder<T, B, I, S> name(String value) {
            name = value;
            return self();
        }
    }
}
