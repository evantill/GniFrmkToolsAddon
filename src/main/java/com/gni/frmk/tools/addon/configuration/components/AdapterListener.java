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

    public AdapterListener(AdapterListenerBuilder builder) {
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

    public static class AdapterListenerBuilder extends AdapterTypeAware.Builder<AdapterListenerBuilder, AdapterListener, StringId, ActivableState> {
        protected String name;

        public AdapterListenerBuilder() {
            defineType(ComponentType.ADAPTER_LISTENER);
        }

        @Override
        public AdapterListenerBuilder self() {
            return this;
        }

        public AdapterListenerBuilder name(String value) {
            name = value;
            defineId(new StringId(name));
            return self();
        }

        @Override
        public AdapterListener build() {
            return new AdapterListener(this);
        }

        @Override
        public AdapterListenerBuilder from(AdapterListener source) {
            super.from(source);
            name = source.getName();
            return self();
        }
    }

}
