package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.visitors.ComponentVisitor;

import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:09
 *
 * @author: e03229
 */
public class AdapterNotification extends AdapterTypeAware<StringId, ActivableState> {

    @NotNull
    private final String name;

    public AdapterNotification(Builder<?, ?, StringId, ActivableState> builder) {
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

    public static AdapterNotificationBuilder builder() {
        return new AdapterNotificationBuilder();
    }

    public static class AdapterNotificationBuilder extends Builder<AdapterNotificationBuilder, AdapterNotification, StringId, ActivableState> {

        public AdapterNotificationBuilder() {
            defineType(ComponentType.ADAPTER_NOTIFICATION);
        }

        @Override
        public AdapterNotificationBuilder self() {
            return this;
        }

        @Override
        public Builder<AdapterNotificationBuilder, AdapterNotification, StringId, ActivableState> name(String value) {
            super.name(value);
            defineId(new StringId(name));
            return self();
        }

        @Override
        public AdapterNotification build() {
            return new AdapterNotification(this);
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
