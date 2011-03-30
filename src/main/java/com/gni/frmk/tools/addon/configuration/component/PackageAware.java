package com.gni.frmk.tools.addon.configuration.component;

import com.gni.frmk.tools.addon.configuration.component.AbstractComponent.AbstractComponentId;
import com.gni.frmk.tools.addon.configuration.component.AbstractComponent.AbstractComponentState;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 13:29
 *
 * @author: e03229
 */
public abstract class PackageAware<I extends AbstractComponentId, S extends AbstractComponentState> extends AbstractComponent<I, S> {

    @NotNull
    @XmlElement
    private final String packageName;

    protected PackageAware(Builder<?, ?, I, S> builder) {
        super(builder);
        packageName = builder.packageName;
    }

    protected PackageAware(){
        super();
        packageName = null;
    }
    public String getPackageName() {
        return packageName;
    }

    public static abstract class Builder<T extends Builder<T, B, I, S>, B extends PackageAware<I, S>, I extends AbstractComponentId, S extends AbstractComponentState>
            extends AbstractComponent.Builder<T, B, I, S> {

        @NotNull
        protected String packageName;

        public Builder<T, B, I, S> packageName(String value) {
            packageName = checkNotNull(value);
            return self();
        }

        @Override
        public T from(B source) {
            super.from(source);
            packageName = source.getPackageName();
            return self();
        }
    }
}
