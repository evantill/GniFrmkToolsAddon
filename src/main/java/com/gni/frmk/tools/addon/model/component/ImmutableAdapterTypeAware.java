package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentId;
import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.model.component.ImmutableAdapterTypeAware.MutableAdapterTypeAware;

/**
 * Created by IntelliJ IDEA.
 * Date: 14/03/11
 * Time: 11:52
 *
 * @author: e03229
 */
public abstract class ImmutableAdapterTypeAware
        <IC extends ImmutableAdapterTypeAware<IC, MC, I, S>,
                MC extends MutableAdapterTypeAware<IC, MC, I, S>,
                I extends AbstractComponentId,
                S extends AbstractComponentState>
        extends ImmutablePackageAware<IC, MC, I, S>
        implements ReadableAdapterTypeAware<IC, MC, I, S> {

    private final String adapterType;

    protected ImmutableAdapterTypeAware(ReadableAdapterTypeAware<IC, MC, I, S> source) {
        super(source);
        adapterType = source.getAdapterType();
    }

    public String getAdapterType() {
        return adapterType;
    }

    public static abstract class MutableAdapterTypeAware
            <IC extends ImmutableAdapterTypeAware<IC, MC, I, S>,
                    MC extends MutableAdapterTypeAware<IC, MC, I, S>,
                    I extends AbstractComponentId,
                    S extends AbstractComponentState>
            extends MutablePackageAware<IC, MC, I, S>
            implements WritableAdapterTypeAware<IC, MC, I, S> {

        protected String adapterType;

        protected MutableAdapterTypeAware(ReadableAdapterTypeAware<IC, MC, I, S> source) {
            super(source);
            setAdapterType(source.getAdapterType());
        }

        protected MutableAdapterTypeAware() {
        }

        public String getAdapterType() {
            return adapterType;
        }

        public void setAdapterType(String adapterType) {
            this.adapterType = adapterType;
        }
    }
}
