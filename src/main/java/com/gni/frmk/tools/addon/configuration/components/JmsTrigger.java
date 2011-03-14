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

    public JmsTrigger(JmsTriggerBuilder builder) {
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

    public static class JmsTriggerBuilder extends PackageAware.Builder<JmsTriggerBuilder, JmsTrigger, StringId, ActivableState> {
        private String name;

        public JmsTriggerBuilder() {
            defineType(ComponentType.JMS_TRIGGER);
        }

        @Override
        public JmsTriggerBuilder self() {
            return this;
        }

        public JmsTriggerBuilder name(String value) {
            name = value;
            defineId(new StringId(name));
            return self();
        }

        @Override
        public JmsTrigger build() {
            return new JmsTrigger(this);
        }
    }

}