package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.component.ImmutablePort.MutablePort;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.ActivableState;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/04/11
 * Time: 16:07
 *
 * @author: e03229
 */
public interface ReadablePort extends ReadablePackageAware<ImmutablePort, MutablePort, StringId, ActivableState> {

    @NotNull
    @XmlElement
    String getKey();


    @NotNull
    @XmlElement
    boolean isPrimary();

    interface WritablePort extends ReadablePort, WritablePackageAware<ImmutablePort, MutablePort, StringId, ActivableState> {
        void setKey(String value);

        void setPrimary(boolean value);
    }
}
