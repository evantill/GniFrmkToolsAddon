package com.gni.frmk.tools.addon.configuration.components;

import com.gni.frmk.tools.addon.configuration.components.Component.ComponentId;
import com.gni.frmk.tools.addon.configuration.components.Component.ComponentState;

import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 13:29
 *
 * @author: e03229
 */
public abstract class PackageAwareComponent<I extends ComponentId, S extends ComponentState> extends BaseComponent<I, S> {

    @NotNull
    private final String packageName;

    protected PackageAwareComponent(Builder<?, ?, I, S> builder) {
        super(builder);
        packageName = builder.packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public static abstract class Builder<T extends Builder<T, B, I, S>, B extends BaseComponent<I, S>, I extends ComponentId, S extends ComponentState>
            extends BaseComponent.Builder<T, B, I, S> {

        protected String packageName;

        public Builder<T, B, I, S> packageName(String value) {
            packageName = value;
            return self();
        }
    }
}
