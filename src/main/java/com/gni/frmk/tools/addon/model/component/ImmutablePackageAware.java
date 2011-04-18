package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.api.ComponentId;
import com.gni.frmk.tools.addon.model.api.ComponentState;
import com.gni.frmk.tools.addon.model.api.ImmutableComponent;
import com.gni.frmk.tools.addon.model.api.ImmutableComponent.MutableComponent;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 13:29
 *
 * @author: e03229
 */
public abstract class ImmutablePackageAware
        <IC extends ImmutableComponent<IC, MC, I, S>,
                MC extends MutableComponent<IC, MC, I, S>,
                I extends ComponentId,
                S extends ComponentState>
        extends ImmutableComponent<IC, MC, I, S>
        implements ReadablePackageAware<IC, MC, I, S> {
    private final String packageName;

    protected ImmutablePackageAware(ReadablePackageAware<IC, MC, I, S> source) {
        super(source);
        packageName = source.getPackageName();
    }

    public String getPackageName() {
        return packageName;
    }

    public abstract static class MutablePackageAware
            <IC extends ImmutableComponent<IC, MC, I, S>,
                    MC extends MutableComponent<IC, MC, I, S>,
                    I extends ComponentId,
                    S extends ComponentState>
            extends MutableComponent<IC, MC, I, S>
            implements WritablePackageAware<IC, MC, I, S> {

        private String packageName;

        protected MutablePackageAware(ReadablePackageAware<IC, MC, I, S> source) {
            super(source);
            setPackageName(source.getPackageName());
        }

        protected MutablePackageAware() {
        }

        @Override
        public void setPackageName(String value) {
            packageName = value;

        }

        public String getPackageName() {
            return packageName;
        }
    }
}
