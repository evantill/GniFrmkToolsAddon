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

    public AdapterConnection(AdapterConnectionBuilder builder) {
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

    public static class AdapterConnectionBuilder extends  AdapterTypeAware.Builder<AdapterConnectionBuilder,AdapterConnection,StringId,EnableState>  {

        protected String alias;

        public AdapterConnectionBuilder() {
            defineType(ComponentType.ADAPTER_CONNECTION);
        }

           public AdapterConnectionBuilder alias(String value) {
            alias = value;
           defineId(new StringId(alias));
            return self();
        }

        @Override
        public AdapterConnectionBuilder self() {
            return this;
        }

        @Override
        public AdapterConnectionBuilder from(AdapterConnection source) {
            super.from(source);
            alias = source.getAlias();
            return self();
        }

        @Override
        public AdapterConnection build() {
            return new AdapterConnection(this);
        }
    }

}
