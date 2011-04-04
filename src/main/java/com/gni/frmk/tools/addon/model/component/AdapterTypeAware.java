package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentId;
import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentState;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 11:52
 *
 * @author: e03229
 */
public abstract class AdapterTypeAware<I extends AbstractComponentId, S extends AbstractComponentState> extends PackageAware<I, S> {

    @NotNull
    @XmlElement
    private final String adapterType;

    protected AdapterTypeAware() {
        super();
        adapterType = null;
    }

    protected AdapterTypeAware(Builder<?, ?, I, S> builder) {
        super(builder);
        adapterType=builder.adapterType;
    }

    public String getAdapterType() {
        return adapterType;
    }

    public static abstract class Builder<T extends Builder<T, B, I, S>, B extends AdapterTypeAware<I, S>, I extends AbstractComponentId, S extends AbstractComponentState>
            extends PackageAware.Builder<T, B, I, S> {

        @NotNull
        protected String adapterType;

        public Builder<T, B, I, S> adapterType(String value) {
            adapterType = value;
            return self();
        }

        @Override
        public T from(B source) {
            super.from(source);
            adapterType=source.getAdapterType();
            return self();
        }
    }
}
