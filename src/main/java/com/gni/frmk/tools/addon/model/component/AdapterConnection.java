package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.api.ComponentType;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.EnableState;
import com.gni.frmk.tools.addon.service.api.component.ComponentVisitor;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 09/03/11
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class AdapterConnection extends AdapterTypeAware<StringId, EnableState> {

    @NotNull
    @XmlElement
    private final String alias;

    public AdapterConnection(AdapterConnectionBuilder builder) {
        super(builder);
        alias = builder.alias;
    }

    private AdapterConnection() {
        super();
        alias=null;
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

        @NotNull
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
        protected AdapterConnection buildObjectBeforeValidation() {
            return new AdapterConnection(this);
        }
    }

}
