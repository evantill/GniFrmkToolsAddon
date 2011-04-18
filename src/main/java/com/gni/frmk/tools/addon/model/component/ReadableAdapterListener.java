package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.ImmutableAdapterListener.MutableAdapterListener;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/04/11
 * Time: 17:04
 *
 * @author: e03229
 */
public interface ReadableAdapterListener
        extends ReadableAdapterTypeAware<ImmutableAdapterListener, MutableAdapterListener, StringId, ActivableState> {

    @NotNull
    @XmlElement
    String getName();

    interface WritableAdapterListener
            extends ReadableAdapterListener,
            WritableAdapterTypeAware<ImmutableAdapterListener, MutableAdapterListener, StringId, ActivableState> {

        void setName(String value);
    }
}
