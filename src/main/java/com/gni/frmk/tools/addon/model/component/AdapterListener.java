package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.api.ComponentType;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;
import com.gni.frmk.tools.addon.visitor.api.ComponentVisitor;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 13:08
 *
 * @author: e03229
 */
@XmlRootElement
public class AdapterListener extends AdapterTypeAware<StringId, ActivableState> {

    @NotNull
    @XmlElement
    private final String name;

    public AdapterListener(AdapterListenerBuilder builder) {
        super(builder);
        name = builder.name;
    }

    private AdapterListener() {
        super();
        name = null;
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
        @NotNull
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
        protected AdapterListener buildObjectBeforeValidation() {
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
