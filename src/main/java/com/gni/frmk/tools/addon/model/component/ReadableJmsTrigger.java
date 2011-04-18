package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.ImmutableJmsTrigger.MutableJmsTrigger;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/04/11
 * Time: 17:22
 *
 * @author: e03229
 */
public interface ReadableJmsTrigger
        extends ReadablePackageAware<ImmutableJmsTrigger, MutableJmsTrigger, StringId, ActivableState> {

    @NotNull
    @XmlElement
    String getName();

    interface WritableJmsTrigger
            extends ReadablePackageAware<ImmutableJmsTrigger, MutableJmsTrigger, StringId, ActivableState>,
            ReadableJmsTrigger {

        void setName(String value);
    }
}
