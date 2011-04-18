package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.api.ReadableComponent;
import com.gni.frmk.tools.addon.model.component.ImmutableNativeTrigger.MutableNativeTrigger;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.NativeTriggerState;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/04/11
 * Time: 17:22
 *
 * @author: e03229
 */
public interface ReadableNativeTrigger
        extends ReadableComponent<ImmutableNativeTrigger, MutableNativeTrigger, StringId, NativeTriggerState> {

    @NotNull
    @XmlElement
    String getName();

    interface WritableNativeTrigger
            extends ReadableNativeTrigger,
            WritableComponent<ImmutableNativeTrigger, MutableNativeTrigger, StringId, NativeTriggerState> {
        void setName(String value);
    }
}
