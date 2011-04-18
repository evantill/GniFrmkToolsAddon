package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentId;
import com.gni.frmk.tools.addon.model.component.AbstractComponent.AbstractComponentState;
import com.gni.frmk.tools.addon.model.component.ImmutableAdapterTypeAware.MutableAdapterTypeAware;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/04/11
 * Time: 16:43
 *
 * @author: e03229
 */
public interface ReadableAdapterTypeAware
        <IC extends ImmutableAdapterTypeAware<IC, MC, I, S>,
                MC extends MutableAdapterTypeAware<IC, MC, I, S>,
                I extends AbstractComponentId,
                S extends AbstractComponentState>
        extends ReadablePackageAware<IC, MC, I, S> {


    @NotNull
    @XmlElement
    String getAdapterType();

    interface WritableAdapterTypeAware
            <IC extends ImmutableAdapterTypeAware<IC, MC, I, S>,
                    MC extends MutableAdapterTypeAware<IC, MC, I, S>,
                    I extends AbstractComponentId,
                    S extends AbstractComponentState>
            extends ReadableAdapterTypeAware<IC, MC, I, S>,
            WritablePackageAware<IC, MC, I, S> {
        void setAdapterType(String value);
    }
}
