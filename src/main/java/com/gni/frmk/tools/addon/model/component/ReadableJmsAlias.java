package com.gni.frmk.tools.addon.model.component;

import com.gni.frmk.tools.addon.model.api.ReadableComponent;
import com.gni.frmk.tools.addon.model.component.ImmutableJmsAlias.MutableJmsAlias;
import com.gni.frmk.tools.addon.model.component.id.StringId;
import com.gni.frmk.tools.addon.model.component.state.ConnectableState;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by IntelliJ IDEA.
 * Date: 18/04/11
 * Time: 17:21
 *
 * @author: e03229
 */
public interface ReadableJmsAlias
        extends ReadableComponent<ImmutableJmsAlias, MutableJmsAlias, StringId, ConnectableState> {


    @NotNull
    @XmlElement
    String getName();

    @NotNull
    @XmlElement
    String getDescription();

    interface WritableJmsAlias
            extends ReadableJmsAlias,
            WritableComponent<ImmutableJmsAlias, MutableJmsAlias, StringId, ConnectableState> {
        void setName(String value);

        void setDescription(String value);

    }
}
