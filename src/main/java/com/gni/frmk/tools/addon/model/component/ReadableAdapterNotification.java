package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.ImmutableAdapterNotification.MutableAdapterNotification;
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
public interface ReadableAdapterNotification
        extends ReadableAdapterTypeAware<ImmutableAdapterNotification, MutableAdapterNotification, StringId, ActivableState> {

    @NotNull
    @XmlElement
    String getName();

    interface WritableAdapterNotification
            extends ReadableAdapterNotification,
            WritableAdapterTypeAware<ImmutableAdapterNotification, MutableAdapterNotification, StringId, ActivableState> {

        void setName(String value);
    }
}